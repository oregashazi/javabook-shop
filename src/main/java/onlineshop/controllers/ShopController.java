package onlineshop.controllers;

import jakarta.servlet.http.HttpSession;
import onlineshop.Shop;
import onlineshop.enums.Sorting;
import onlineshop.merchandise.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ShopController extends FunctionController {
    private static Logger log = LogManager.getLogger(ShopController.class);
    public static final int PAGE_SIZE = 12;

    @Autowired
    Shop shop;

    @GetMapping(value = {"/"})
    public String root(Model model) {
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/index.html"})
    public String homePage(Model model,
                           @RequestParam(name = "sort", required = false) Sorting sort,
                           @RequestParam(name = "page", required = false) Integer page,
                           @RequestParam(name = "s", required = false) String search,
                           HttpSession session) {

        sort = (Sorting) shop.getSessionParam(session, "sort", sort, Sorting.DEFAULT);
        page = (Integer) shop.getSessionParam(session, "page", page, 1);
        search = (String) shop.getSessionParam(session, "s", search, "");

        // Count (TO) and (FROM)
        int from = (page - 1) * PAGE_SIZE;
        int to = Math.min(from + PAGE_SIZE, shop.getNumOfArticles());

        // Create a synchronized list copy
        List<Book> books = new ArrayList<>(shop.sortAndPaginateArticles(sort, from, to, search));
        List<Book> synchronizedBooks = Collections.synchronizedList(books);

        getCartItems(model);

        log.info("success. number of books : {}", synchronizedBooks.size());
        model.addAttribute("books", synchronizedBooks);
        model.addAttribute("nrofb", synchronizedBooks.size());
        model.addAttribute("sortings", Sorting.values());
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);
        handleSortingPagination(model, sort, page, search);
        return "booklist";
    }

    @GetMapping(value = {"/{name}.html"})
    public String htmlMapping(Model model, @PathVariable("name") String name, HttpSession session) {
        getCartItems(model);
        return name;
    }

    private void handleSortingPagination(Model model, Sorting sorting, Integer page, String search) {
        int numOfArticles = shop.getNumOfArticles();
        int from = Math.max((page - 1) * PAGE_SIZE, 0);
        int to = Math.min(numOfArticles, from + PAGE_SIZE);
        List<Book> articles = shop.sortAndPaginateArticles(sorting, from, to, search);

        log.info("success. number of articles in handleSotringPagination : {}", articles.size());

        model.addAttribute("articles", articles);
        model.addAttribute("from", ++from);
        model.addAttribute("to", to);
        model.addAttribute("numOfArticles", numOfArticles);

        int pageCount = (numOfArticles / PAGE_SIZE) + 1;
        Map<Integer, String> pages = new HashMap<>();
        for (int pageNumber = 1; pageNumber <= pageCount; pageNumber++) {
            String active = (pageNumber == page) ? "active" : "";
            pages.put(pageNumber, active);
        }

        model.addAttribute("pageCount", pageCount);
        model.addAttribute("pages", pages.entrySet());
        model.addAttribute("prevPage", Math.max(page - 1, 1));
        model.addAttribute("nextPage", Math.min(page + 1, pageCount));

        createSortingLinks(model, sorting);
    }

    private void createSortingLinks(Model model, Sorting currentSort) {
        List<Map<String, String>> sortings = new ArrayList<>();
        for (Sorting sorting : Sorting.values()) {
            Map<String, String> sortingMap = new HashMap<>();
            sortingMap.put("value", sorting.getValue());
            sortingMap.put("label", sorting.getLabel());
            sortingMap.put("selected", (sorting == currentSort) ? "selected" : "");
            sortings.add(sortingMap);
        }
        model.addAttribute("sortings", sortings);
        model.addAttribute("sort", currentSort.getValue());
    }

}

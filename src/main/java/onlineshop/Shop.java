package onlineshop;

import jakarta.servlet.http.HttpSession;
import onlineshop.enums.Sorting;
import onlineshop.merchandise.Article;
import onlineshop.merchandise.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;

//import java.util.*;

/**
 * Manages the Shop
 */
@SpringBootApplication
public class Shop {
    public static final DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));
    private final static Logger log = LogManager.getLogger(Shop.class);
    private static List<Book> books = new ArrayList<>();
    private static List<Book> filteredBooks  = new ArrayList<>();
    private List<Book> new_book = new ArrayList<>();

    public static void main(String[] args) {
        final String CSV_FILE_PATH = "src/main/resources/books.csv";
        books = CSVReader.readBooksFromCSV(CSV_FILE_PATH);
        SpringApplication.run(Shop.class, args);
        log.info("http://localhost:8080");
    }


    public List<Book> getBooks() {
        return books;
    }

    public int getNumOfArticles() {
        if (filteredBooks.size() == 0) {
            return 0;
        }
        return filteredBooks.size();
    }

    public Book getBookById(int id) {
        for (Book book : new_book) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null; // Книга не найдена
    }


    public List<Book> sortAndPaginateArticles(Sorting sorting, int from, int to, String search) {
        sortArticles(sorting);
        filteredBooks = filterArticlesByTitle(search);
        return filteredBooks.subList(Math.min(from, filteredBooks.size()), Math.min(to, filteredBooks.size()));
    }


    private List<Book> filterArticlesByTitle(String search) {
        if (search == null || search.trim().isEmpty()) {
            return new ArrayList<>(books);
        }
        String searchLower = search.toLowerCase();
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchLower))
                .collect(Collectors.toList());
    }


    private void sortArticles(Sorting sorting) {
        switch (sorting) {
            case DEFAULT:
            case ALPHA_UP:
                books.sort(Comparator.comparing(Book::getTitle));
                break;
            case ALPHA_DOWN:
                books.sort((a, b) -> b.getTitle().compareTo(a.getTitle()));
                break;
            case PRICE_UP:
                books.sort(Comparator.comparingDouble(Book::getPrice));
                break;
            case PRICE_DOWN:
                books.sort((a, b) -> Double.compare(b.getPrice(), a.getPrice()));
                break;
        }
    }

    public Object getSessionParam(HttpSession session, String paramName, Object paramValue, Object defaultValue) {
        if (paramValue == null) {
            Object sessionValue = session.getAttribute(paramName);
            paramValue = sessionValue != null ? sessionValue : defaultValue;
        }
        session.setAttribute(paramName, paramValue);
        return paramValue;
    }

    public Book getArticleByNumber(int articleNo) {
        for (Book book : books) {
            if (book.getArticleNo() == articleNo)
                return book;
        }
        return null;
    }
}
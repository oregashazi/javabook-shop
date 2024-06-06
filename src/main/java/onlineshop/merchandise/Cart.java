package onlineshop.merchandise;

import onlineshop.Shop;
import onlineshop.merchandise.Book;
import onlineshop.merchandise.CartItem;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Counts cart items and their quantity
     * @return numberOfItems {@link Integer}
     */
    public int getNumOfItems() {
        int numOfItems = 0;
        for (CartItem item : items) {
            numOfItems += item.getQuantity();
        }
        return numOfItems;
    }

    /**
     * Sums all cart items, taking their quantity into account
     * @return formattedNumber {@link String}
     */
    public String getGrandTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return Shop.df.format(total);
    }


    public void addArticle(Book book) {
        CartItem item = findItem(book.getArticleNo());
        if (item == null) {
            item = new CartItem(book);
            items.add(item);
        }
        item.increaseQuantity();
    }

    public boolean increaseQuantity(int articleNo) {
        CartItem existingItem = findItem(articleNo);
        if (existingItem != null) {
            existingItem.increaseQuantity();
            return true;
        }
        return false;
    }

    public boolean setQuantity(int articleNo, int quantity) {
        CartItem existingItem = findItem(articleNo);
        if (existingItem != null) {
            existingItem.setQuantity(quantity);
            return true;
        }
        return false;
    }


    public boolean decreaseQuantity(int articleNo) {
        CartItem existingItem = findItem(articleNo);
        if (existingItem != null) {
            existingItem.decreaseQuantity();
            if (existingItem.getQuantity() < 1) {
                items.remove(existingItem);
                return false;
            }
            return true;
        }
        return false;
    }


    public boolean removeArticle(int articleNo) {
        CartItem existingItem = findItem(articleNo);
        if (existingItem != null) {
            items.remove(existingItem);
            return true;
        }
        return false;
    }


    private CartItem findItem(int articleNo) {
        for (CartItem item : items) {
            if (item.getArticleNo() == articleNo) {
                return item;
            }
        }
        return null;
    }
}

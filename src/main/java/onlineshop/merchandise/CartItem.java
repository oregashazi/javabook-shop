//package onlineshop.merchandise;
//
//import onlineshop.Shop;
//
///**
// * Represents an article/book in the shopping cart
// */
//public class CartItem extends Book {
//    private int quantity = 0;
//
//    public CartItem(Book book) {
//        this.articleNo = book.articleNo;
//        this.title = book.title;
//        this.manufacturer = book.manufacturer;
//        this.price = book.price;
//        this.image = book.image;
//        this.discount = book.discount;
//        this.author = book.author;
//        this.pages = book.pages;
//    }
//
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public boolean getShowQuantity() {
//        return quantity > 1;
//    }
//
//    public void increaseQuantity() {
//        quantity++;
//    }
//
//    public void decreaseQuantity() {
//        quantity--;
//    }
//
//    public double getTotalPrice() {
//        return quantity * price;
//    }
//
//    public String getTotalPriceFormatted() {
//        return Shop.df.format(getTotalPrice());
//    }
//
//
//}

package onlineshop.merchandise;

import onlineshop.Shop;

public class CartItem extends Book {
    private int quantity = 1;

    public CartItem(Book book) {
        super();
        this.setId(book.getId());
        this.setTitle(book.getTitle());
        this.setDescription(book.getDescription());
        this.setAuthor(book.getAuthor());
        this.setImage(book.getImage());
        this.setPages(book.getPages());
        this.setPrice(book.getPrice());
        this.setCategory(book.getCategory());
        this.setDiscount(book.getDiscount());
        this.quantity = 1;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getShowQuantity() {
        return quantity > 1;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {
        quantity--;
    }

    public double getTotalPrice() {
        return quantity * getPrice();
    }

    public String getTotalPriceFormatted() {
        return Shop.df.format(getTotalPrice());
    }
}

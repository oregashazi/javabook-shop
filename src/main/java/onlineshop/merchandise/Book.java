package onlineshop.merchandise;

import java.util.ArrayList;
import java.util.List;

public class Book extends Article {
    public String title;
    public String description;
    public String author;
    public String image;
    public int pages;
    public double price;
    public String category;
    public int discount;
    public int id;


    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAll() {
        return title + " " + description + " " + author + " " + image + " " + pages + " " + price;
    }

    public String getCategory() {
        return category;
    }

    public int getDiscount() {
        return discount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


package onlineshop.merchandise;

import onlineshop.enums.Category;
import onlineshop.enums.Format;

public class Book extends Article {
    protected int pages;
    protected String title;
    protected String author;
    protected Format format;
    protected Category category;

    public Book() {
        super();
    }

    public Book(String title, String author) {
        super();
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, Format format, Category category, int pages) {
        super();
        this.title = title;
        this.author = author;
        this.format = format;
        this.category = category;
        this.pages = pages;
    }
}

package onlineshop.merchandise;

public class Article {
  private static int articleCounter = 1;
  /** Description of this Article */
  protected String description;
  /** Manufacturer of this Article */
  protected String manufacturer;
  /** Unique article number */
  protected int articleNo;

  public Article() {
    this.articleNo = Article.articleCounter++;
  }

  public Article(String description, String manufacturer) {
    this();
    this.description = description;
    this.manufacturer = manufacturer;
  }
}

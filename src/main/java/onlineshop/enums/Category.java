package onlineshop.enums;

public enum Category {
    ARTS("Arts & Photography"),
    BUSINESS("Business & Money"),
    CALENDARS("Calendars"),
    COMICS("Comics & Graphic Novels"),
    COMPUTERS("Computers & Technology"),
    COOKBOOKS("Cookbooks, Food & Wine"),
    CRAFTS("Crafts, Hobbies & Home"),
    EDUCATION("Education & Teaching"),
    HEALTH("Health, Fitness & Dieting"),
    HUMOR("Humor & Entertainment"),
    FICTION("Literature & Fiction"),
    THRILLER("Mystery, Thriller & Suspense"),
    POLITICS("Politics & Social Sciences"),
    ROMANCE("Romance"),
    SCIENCE("Science & Math"),
    SCIFI("Science Fiction & Fantasy"),
    TEEN("Teen & Young Adult"),
    TRAVEL("Travel");

    public final String label;

    private Category(String label) {
        this.label = label;
    }

}

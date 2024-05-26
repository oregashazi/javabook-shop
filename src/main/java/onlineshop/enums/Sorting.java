package onlineshop.enums;

public enum Sorting {
    DEFAULT("Default sorting"),
    ALPHA_UP("Name (A - Z)"),
    ALPHA_DOWN("Name (Z - A)"),
    PRICE_UP("Price (Low-High)"),
    PRICE_DOWN("Price (High-Low)");

    private final String label;
    private String selected;

    Sorting(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return name();
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}


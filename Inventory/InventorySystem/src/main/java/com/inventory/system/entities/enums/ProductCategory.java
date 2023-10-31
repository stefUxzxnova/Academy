package com.inventory.system.entities.enums;

public enum ProductCategory {
    Electronics("Electronics", 1),
    MakeUp("MakeUp", 2),
    HomeDecor("HomeDecor", 3),
    AutomotiveParts("AutomotiveParts", 4),
    SportingGoods("SportingGoods", 5),
    Hardware("Hardware", 6),
    ToysAndGames("ToysAndGames", 7);

    private final String displayName;
    private final int code;

    ProductCategory(String displayName, int code) {
        this.displayName = displayName;
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getCode() {
        return code;
    }

    // You can also provide a method to get a category by its code
    public static ProductCategory getCategoryByCode(int code) {
        for (ProductCategory category : values()) {
            if (category.getCode() == code) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category code: " + code);
    }
}



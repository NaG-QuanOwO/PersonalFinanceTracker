package com.example.personalfinancetracker.data.model;

public class CategorySummary {
    private int categoryId;
    private String categoryName;
    private String iconName;
    private String type;
    private long totalAmount;

    public CategorySummary(int categoryId, String categoryName, String iconName, String type, long totalAmount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.iconName = iconName;
        this.type = type;
        this.totalAmount = totalAmount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }
}

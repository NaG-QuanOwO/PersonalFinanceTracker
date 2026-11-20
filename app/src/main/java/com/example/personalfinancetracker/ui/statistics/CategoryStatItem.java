package com.example.personalfinancetracker.ui.statistics;

/**
 * UI Model đại diện cho một danh mục thống kê chi tiêu.
 * Đây là UI Model thuần túy, KHÔNG chỉnh sửa hoặc thay thế Entity của TV1.
 */
public class CategoryStatItem {
    private final String categoryName;
    private final double amount;
    private final double percentage;
    private final String colorHex;

    public CategoryStatItem(String categoryName, double amount, double percentage, String colorHex) {
        this.categoryName = categoryName;
        this.amount = amount;
        this.percentage = percentage;
        this.colorHex = colorHex;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getAmount() {
        return amount;
    }

    public double getPercentage() {
        return percentage;
    }

    public String getColorHex() {
        return colorHex;
    }
}
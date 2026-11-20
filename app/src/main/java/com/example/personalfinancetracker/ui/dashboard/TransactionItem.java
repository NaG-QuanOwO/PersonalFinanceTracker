package com.example.personalfinancetracker.ui.dashboard;

/**
 * UI Model đại diện cho một giao dịch hiển thị trên Dashboard.
 * Đây là UI Model thuần túy, KHÔNG chỉnh sửa hoặc thay thế Entity của TV1.
 */
public class TransactionItem {
    private final String id;
    private final String title;
    private final String category;
    private final double amount;
    private final String date;
    private final boolean isIncome;

    public TransactionItem(String id, String title, String category, double amount, String date, boolean isIncome) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.isIncome = isIncome;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public boolean isIncome() {
        return isIncome;
    }
}
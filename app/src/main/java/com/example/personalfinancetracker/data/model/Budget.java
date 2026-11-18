package com.example.personalfinancetracker.data.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "budgets",
        foreignKeys = @ForeignKey(entity = Category.class,
                                  parentColumns = "id",
                                  childColumns = "categoryId",
                                  onDelete = ForeignKey.RESTRICT),
        indices = {@Index("categoryId")}
)
public class Budget {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private double targetAmount;
    private long startDateInMillis;
    private long endDateInMillis;
    private int categoryId;

    public Budget(double targetAmount, long startDateInMillis, long endDateInMillis, int categoryId) {
        this.targetAmount = targetAmount;
        this.startDateInMillis = startDateInMillis;
        this.endDateInMillis = endDateInMillis;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public long getStartDateInMillis() {
        return startDateInMillis;
    }

    public void setStartDateInMillis(long startDateInMillis) {
        this.startDateInMillis = startDateInMillis;
    }

    public long getEndDateInMillis() {
        return endDateInMillis;
    }

    public void setEndDateInMillis(long endDateInMillis) {
        this.endDateInMillis = endDateInMillis;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}

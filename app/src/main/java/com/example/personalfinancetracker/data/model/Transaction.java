package com.example.personalfinancetracker.data.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactions",
        foreignKeys = @ForeignKey(entity = Category.class,
                                  parentColumns = "id",
                                  childColumns = "categoryId",
                                  onDelete = ForeignKey.RESTRICT),
        indices = {@Index("categoryId")}
)
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private double amount;
    private String note;
    private long dateInMillis;
    private int categoryId;

    public Transaction(double amount, String note, long dateInMillis, int categoryId) {
        this.amount = amount;
        this.note = note;
        this.dateInMillis = dateInMillis;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getDateInMillis() {
        return dateInMillis;
    }

    public void setDateInMillis(long dateInMillis) {
        this.dateInMillis = dateInMillis;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}

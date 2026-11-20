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

    private long amount;
    private String note;
    private long dateInMillis;
    private int categoryId;

    public Transaction(long amount, String note, long dateInMillis, int categoryId) {
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
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

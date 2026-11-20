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

    private String monthKey;
    private long limitAmount;


    public Budget(long limitAmount, String monthKey) {
        this.limitAmount = limitAmount;
        this.monthKey = monthKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLimitAmount() {
        return limitAmount;
    }

    public String getMonthKey() {
        return monthKey;
    }

    public void setLimitAmount(long limitAmount) {
        this.limitAmount = limitAmount;
    }

    public void setMonthKey(String monthKey) {
        this.monthKey = monthKey;
    }
}


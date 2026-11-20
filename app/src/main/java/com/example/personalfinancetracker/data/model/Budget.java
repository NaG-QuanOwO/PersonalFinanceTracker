package com.example.personalfinancetracker.data.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "budgets",
        indices = {@Index(value = {"monthKey"}, unique = true)}
)
public class Budget {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String monthKey;
    private long limitAmount;

    public Budget(String monthKey, long limitAmount) {
        if (limitAmount <= 0) {
            throw new IllegalArgumentException("limitAmount must be greater than 0");
        }
        this.monthKey = monthKey;
        this.limitAmount = limitAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonthKey() {
        return monthKey;
    }

    public void setMonthKey(String monthKey) {
        this.monthKey = monthKey;
    }

    public long getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(long limitAmount) {
        if (limitAmount <= 0) {
            throw new IllegalArgumentException("limitAmount must be greater than 0");
        }
        this.limitAmount = limitAmount;
    }
}

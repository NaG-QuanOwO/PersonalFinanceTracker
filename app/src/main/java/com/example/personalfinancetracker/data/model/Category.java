package com.example.personalfinancetracker.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String type;
    private String iconName;

    public Category(String name, String type, String iconName) {
        this.name = name;
        this.type = type;
        this.iconName = iconName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getIconName() {
        return iconName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}

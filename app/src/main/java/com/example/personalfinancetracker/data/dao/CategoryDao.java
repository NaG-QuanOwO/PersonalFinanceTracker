package com.example.personalfinancetracker.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.personalfinancetracker.data.model.Category;
import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Query("SELECT * FROM categories ORDER BY name ASC")
    LiveData<List<Category>> getAllCategory();

    @Query("SELECT * FROM categories WHERE type = :type ORDER BY name ASC")
    LiveData<List<Category>> getCategoryByType(String type);

    @Query("SELECT * FROM categories WHERE id = :id LIMIT 1")
    Category getCategoryById(int id);
}

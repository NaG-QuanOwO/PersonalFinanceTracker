package com.example.personalfinancetracker.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.personalfinancetracker.data.dao.CategoryDao;

import com.example.personalfinancetracker.data.database.AppDatabase;
import com.example.personalfinancetracker.data.model.Category;


import java.util.List;

public class CategoryRepository {
    private final CategoryDao categoryDao;
    private final LiveData<List<Category>> allCategories;


    public CategoryRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        categoryDao = db.categoryDao();
        allCategories = categoryDao.getAllCategory();
    }

    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }

    public LiveData<List<Category>> getCategoriesByType(String type) {
        return categoryDao.getCategoryByType(type);
    }

    public void insert(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            categoryDao.insert(category);
        });
    }

    public void update(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            categoryDao.update(category);
        });
    }

    public void delete(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            categoryDao.delete(category);
        });
    }
}

package com.example.personalfinancetracker.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.personalfinancetracker.data.dao.CategoryDao;
import com.example.personalfinancetracker.data.dao.TransactionDao;
import com.example.personalfinancetracker.data.database.AppDatabase;
import com.example.personalfinancetracker.data.model.Category;

import java.util.List;

public class CategoryRepository {
    private static final String TAG = "CategoryRepository";
    private final CategoryDao categoryDao;
    private final TransactionDao transactionDao;
    private final LiveData<List<Category>> allCategories;

    public interface OperationCallback {
        void onSuccess();
        void onError(String message);
    }

    public CategoryRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        categoryDao = db.categoryDao();
        transactionDao = db.transactionDao();
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
        update(category, null);
    }

    public void update(Category category, OperationCallback callback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            Category existing = categoryDao.getCategoryById(category.getId());
            if (existing != null && !existing.getType().equalsIgnoreCase(category.getType())) {
                int count = transactionDao.getTransactionCountByCategoryId(category.getId());
                if (count > 0) {
                    String errorMsg = "Không thể thay đổi loại danh mục đã có giao dịch!";
                    Log.e(TAG, errorMsg);
                    if (callback != null) {
                        callback.onError(errorMsg);
                    }
                    return;
                }
            }
            categoryDao.update(category);
            if (callback != null) {
                callback.onSuccess();
            }
        });
    }

    public void delete(Category category) {
        delete(category, null);
    }

    public void delete(Category category, OperationCallback callback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            int count = transactionDao.getTransactionCountByCategoryId(category.getId());
            if (count > 0) {
                String errorMsg = "Không thể xóa danh mục đang có giao dịch tham chiếu!";
                Log.e(TAG, errorMsg);
                if (callback != null) {
                    callback.onError(errorMsg);
                }
                return;
            }
            categoryDao.delete(category);
            if (callback != null) {
                callback.onSuccess();
            }
        });
    }
}

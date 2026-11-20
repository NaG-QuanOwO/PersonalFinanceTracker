package com.example.personalfinancetracker.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.personalfinancetracker.data.dao.BudgetDao;
import com.example.personalfinancetracker.data.database.AppDatabase;
import com.example.personalfinancetracker.data.model.Budget;

import java.util.List;

public class BudgetRepository {
    private final BudgetDao budgetDao;
    private final LiveData<List<Budget>> allBudgets;

    public BudgetRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        budgetDao = db.budgetDao();
        allBudgets = budgetDao.getAllBudgets();
    }

    public LiveData<List<Budget>> getAllBudgets() {
        return allBudgets;
    }

    public LiveData<Budget> getBudgetByMonth(String monthKey) {
        return budgetDao.getBudgetByMonth(monthKey);
    }

    public void saveOrUpdateBudget(String monthKey, long limitAmount) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            Budget existing = budgetDao.getBudgetByMonthSync(monthKey);
            if (existing == null) {
                Budget newBudget = new Budget(monthKey, limitAmount);
                budgetDao.insert(newBudget);
            } else {
                existing.setLimitAmount(limitAmount);
                budgetDao.update(existing);
            }
        });
    }

    public void insert(Budget budget) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            budgetDao.insert(budget);
        });
    }

    public void update(Budget budget) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            budgetDao.update(budget);
        });
    }

    public void delete(Budget budget) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            budgetDao.delete(budget);
        });
    }
}

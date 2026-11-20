package com.example.personalfinancetracker.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.personalfinancetracker.data.model.Budget;
import java.util.List;

@Dao
public interface BudgetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Budget budget);

    @Update
    void update(Budget budget);

    @Delete
    void delete(Budget budget);

    @Query("SELECT * FROM budgets ORDER BY monthKey ASC")
    LiveData<List<Budget>> getAllBudgets();

    @Query("SELECT * FROM budgets WHERE monthKey = :monthKey LIMIT 1")
    LiveData<Budget> getBudgetByMonth(String monthKey);

    @Query("SELECT * FROM budgets WHERE monthKey = :monthKey LIMIT 1")
    Budget getBudgetByMonthSync(String monthKey);
}

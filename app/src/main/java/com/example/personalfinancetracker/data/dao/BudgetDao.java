package com.example.personalfinancetracker.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.personalfinancetracker.data.model.Budget;
import java.util.List;

@Dao
public interface BudgetDao {
    @Insert
    void insert(Budget budget);

    @Update
    void update(Budget budget);

    @Delete
    void delete(Budget budget);


    @Query("select * from budgets order by endDateInMillis asc")
    LiveData<List<Budget>> getAllBudgets();

    @Query("select * from budgets where categoryId = :categoryId order by endDateInMillis asc")
    LiveData<List<Budget>> getBudgetsByCategoryId(int categoryId);


}

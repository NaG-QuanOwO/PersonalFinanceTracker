package com.example.personalfinancetracker.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.personalfinancetracker.data.model.CategorySummary;
import com.example.personalfinancetracker.data.model.Transaction;
import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void insert(Transaction transaction);

    @Update
    void update(Transaction transaction);

    @Delete
    void delete(Transaction transaction);

    @Query("SELECT * FROM transactions ORDER BY dateInMillis DESC")
    LiveData<List<Transaction>> getAllTransactions();

    @Query("SELECT * FROM transactions WHERE dateInMillis BETWEEN :startTime AND :endTime ORDER BY dateInMillis DESC")
    LiveData<List<Transaction>> getTransactionByDateRange(long startTime, long endTime);

    @Query("SELECT * FROM transactions WHERE categoryId = :categoryId ORDER BY dateInMillis DESC")
    LiveData<List<Transaction>> getTransactionByCategoryId(int categoryId);

    @Query("SELECT COUNT(*) FROM transactions WHERE categoryId = :categoryId")
    int getTransactionCountByCategoryId(int categoryId);

    @Query("SELECT SUM(t.amount) FROM transactions t INNER JOIN categories c ON t.categoryId = c.id WHERE c.type = :type AND t.dateInMillis BETWEEN :startTime AND :endTime")
    LiveData<Long> getTotalAmountByTypeAndDateRange(String type, long startTime, long endTime);

    @Query("SELECT SUM(t.amount) FROM transactions t INNER JOIN categories c ON t.categoryId = c.id WHERE c.type = 'EXPENSE' AND t.dateInMillis BETWEEN :startTime AND :endTime")
    LiveData<Long> getTotalExpenseByDateRange(long startTime, long endTime);

    @Query("SELECT c.id AS categoryId, c.name AS categoryName, c.iconName AS iconName, c.type AS type, SUM(t.amount) AS totalAmount " +
           "FROM transactions t INNER JOIN categories c ON t.categoryId = c.id " +
           "WHERE t.dateInMillis BETWEEN :startTime AND :endTime " +
           "GROUP BY c.id ORDER BY totalAmount DESC")
    LiveData<List<CategorySummary>> getCategorySummariesByDateRange(long startTime, long endTime);
}

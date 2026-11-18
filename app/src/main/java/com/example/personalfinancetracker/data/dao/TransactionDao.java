package com.example.personalfinancetracker.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("select * from transactions order by dateInMillis desc")
    LiveData<List<Transaction>> getAllTransactions();

    @Query("select * from transactions where dateInMillis between :startTime and :endTime order by dateInMillis desc")
    LiveData<List<Transaction>> getTransactionByDateRange(long startTime, long endTime);

    @Query("select * from transactions where categoryId = :categoryId order by dateInMillis desc")
    LiveData<List<Transaction>> getTransactionByCategoryId(int categoryId);
}

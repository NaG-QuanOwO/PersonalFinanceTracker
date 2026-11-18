package com.example.personalfinancetracker.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.personalfinancetracker.data.dao.TransactionDao;
import com.example.personalfinancetracker.data.database.AppDatabase;
import com.example.personalfinancetracker.data.model.Transaction;

import java.util.List;

public class TransactionRepository {
    private final TransactionDao transactionDao;
    private final LiveData<List<Transaction>> allTransactions;


    public TransactionRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        transactionDao = db.transactionDao();
        allTransactions = transactionDao.getAllTransactions();
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    public LiveData<List<Transaction>> getTransactionsByDateRange(long startTime, long endTime) {
        return transactionDao.getTransactionByDateRange(startTime, endTime);
    }

    public LiveData<List<Transaction>> getTransactionsByCategory(int categoryId) {
        return transactionDao.getTransactionByCategoryId(categoryId);
    }

    public void insert(Transaction transaction) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            transactionDao.insert(transaction);
        });
    }

    public void update(Transaction transaction) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            transactionDao.update(transaction);
        });
    }

    public void delete(Transaction transaction) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            transactionDao.delete(transaction);
        });
    }

}

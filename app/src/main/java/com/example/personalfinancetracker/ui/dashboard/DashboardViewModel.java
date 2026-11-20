package com.example.personalfinancetracker.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.personalfinancetracker.ui.mock.MockDataProvider;

import java.util.List;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<Double> totalIncome = new MutableLiveData<>();
    private final MutableLiveData<Double> totalExpense = new MutableLiveData<>();
    private final MutableLiveData<Double> balance = new MutableLiveData<>();
    private final MutableLiveData<List<TransactionItem>> recentTransactions = new MutableLiveData<>();

    public DashboardViewModel() {
        loadDashboardData();
    }

    /**
     * Tải dữ liệu tổng quan tài chính.
     * Hiện tại dùng MockDataProvider. Khi tích hợp TV1, hàm này sẽ gọi Repository.
     */
    public void loadDashboardData() {
        totalIncome.setValue(MockDataProvider.getMockTotalIncome());
        totalExpense.setValue(MockDataProvider.getMockTotalExpense());
        balance.setValue(MockDataProvider.getMockBalance());
        recentTransactions.setValue(MockDataProvider.getMockRecentTransactions());
    }

    public LiveData<Double> getTotalIncome() {
        return totalIncome;
    }

    public LiveData<Double> getTotalExpense() {
        return totalExpense;
    }

    public LiveData<Double> getBalance() {
        return balance;
    }

    public LiveData<List<TransactionItem>> getRecentTransactions() {
        return recentTransactions;
    }
}
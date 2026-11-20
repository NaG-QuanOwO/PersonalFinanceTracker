package com.example.personalfinancetracker.ui.statistics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.personalfinancetracker.ui.mock.MockDataProvider;

import java.util.List;
import java.util.Locale;

public class StatisticsViewModel extends ViewModel {

    private final MutableLiveData<Integer> selectedMonth = new MutableLiveData<>();
    private final MutableLiveData<Integer> selectedYear = new MutableLiveData<>();
    private final MutableLiveData<String> monthYearText = new MutableLiveData<>();

    private final MutableLiveData<Double> monthlyIncome = new MutableLiveData<>();
    private final MutableLiveData<Double> monthlyExpense = new MutableLiveData<>();
    private final MutableLiveData<Integer> expenseRatioPercent = new MutableLiveData<>();
    private final MutableLiveData<List<CategoryStatItem>> categoryStats = new MutableLiveData<>();
    private final MutableLiveData<Boolean> hasData = new MutableLiveData<>();

    public StatisticsViewModel() {
        // Mặc định chọn Tháng 10 năm 2023 (hoặc Tháng/Năm hiện tại)
        int currentMonth = 10; // Tháng 10 theo dữ liệu mẫu
        int currentYear = 2023;
        setMonthYear(currentMonth, currentYear);
    }

    public void setMonthYear(int month, int year) {
        selectedMonth.setValue(month);
        selectedYear.setValue(year);

        String text = String.format(Locale.getDefault(), "Tháng %02d, %d", month, year);
        monthYearText.setValue(text);

        loadStatisticsData(month, year);
    }

    public void previousMonth() {
        int m = selectedMonth.getValue() != null ? selectedMonth.getValue() : 10;
        int y = selectedYear.getValue() != null ? selectedYear.getValue() : 2023;

        m--;
        if (m < 1) {
            m = 12;
            y--;
        }
        setMonthYear(m, y);
    }

    public void nextMonth() {
        int m = selectedMonth.getValue() != null ? selectedMonth.getValue() : 10;
        int y = selectedYear.getValue() != null ? selectedYear.getValue() : 2023;

        m++;
        if (m > 12) {
            m = 1;
            y++;
        }
        setMonthYear(m, y);
    }

    /**
     * Tải dữ liệu thống kê theo Tháng/Năm.
     * Tránh triệt để lỗi chia cho 0 và xử lý trường hợp không có dữ liệu.
     * Khi tích hợp TV1, hàm này sẽ gọi Repository.
     */
    private void loadStatisticsData(int month, int year) {
        double income = MockDataProvider.getMockMonthlyIncome(month, year);
        double expense = MockDataProvider.getMockMonthlyExpense(month, year);
        List<CategoryStatItem> stats = MockDataProvider.getMockCategoryStats(month, year);

        // Trường hợp không có dữ liệu giao dịch cho tháng 11, 12... (để test empty state)
        if (month > 10 && year == 2023) {
            income = 0.0;
            expense = 0.0;
            stats.clear();
        }

        monthlyIncome.setValue(income);
        monthlyExpense.setValue(expense);
        categoryStats.setValue(stats);

        // Đảm bảo an toàn, phòng tránh lỗi chia cho 0 (Divide-by-zero safety)
        double totalTurnover = income + expense;
        if (totalTurnover > 0) {
            int ratio = (int) Math.round((expense / totalTurnover) * 100.0);
            expenseRatioPercent.setValue(ratio);
        } else {
            expenseRatioPercent.setValue(0);
        }

        boolean dataAvailable = (income > 0 || expense > 0 || (stats != null && !stats.isEmpty()));
        hasData.setValue(dataAvailable);
    }

    public LiveData<String> getMonthYearText() {
        return monthYearText;
    }

    public LiveData<Double> getMonthlyIncome() {
        return monthlyIncome;
    }

    public LiveData<Double> getMonthlyExpense() {
        return monthlyExpense;
    }

    public LiveData<Integer> getExpenseRatioPercent() {
        return expenseRatioPercent;
    }

    public LiveData<List<CategoryStatItem>> getCategoryStats() {
        return categoryStats;
    }

    public LiveData<Boolean> getHasData() {
        return hasData;
    }
}
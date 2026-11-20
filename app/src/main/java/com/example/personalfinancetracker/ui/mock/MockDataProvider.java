package com.example.personalfinancetracker.ui.mock;

import com.example.personalfinancetracker.ui.dashboard.TransactionItem;
import com.example.personalfinancetracker.ui.statistics.CategoryStatItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp cung cấp dữ liệu mẫu tạm thời cho Dashboard và Statistics.
 * Khi TV1 (Data Layer / Room DB) hoàn thành, ViewModel sẽ gọi Repository thay vì lớp này.
 */
public class MockDataProvider {

    public static List<TransactionItem> getMockRecentTransactions() {
        List<TransactionItem> list = new ArrayList<>();
        list.add(new TransactionItem("1", "Lương tháng 10", "Thu nhập", 18500000, "25/10/2023", true));
        list.add(new TransactionItem("2", "Mua sắm VinMart", "Mua sắm", 850000, "24/10/2023", false));
        list.add(new TransactionItem("3", "Thanh toán tiền điện", "Hóa đơn", 620000, "23/10/2023", false));
        list.add(new TransactionItem("4", "Đi ăn với bạn bè", "Thực phẩm", 450000, "22/10/2023", false));
        list.add(new TransactionItem("5", "Đổ xăng xe máy", "Di chuyển", 120000, "21/10/2023", false));
        return list;
    }

    public static double getMockTotalIncome() {
        return 22500000.0;
    }

    public static double getMockTotalExpense() {
        return 8340000.0;
    }

    public static double getMockBalance() {
        return getMockTotalIncome() - getMockTotalExpense();
    }

    public static List<CategoryStatItem> getMockCategoryStats(int month, int year) {
        List<CategoryStatItem> list = new ArrayList<>();
        if (month == 10 && year == 2023) {
            list.add(new CategoryStatItem("Thực phẩm", 3200000, 38.4, "#F59E0B"));
            list.add(new CategoryStatItem("Mua sắm", 2100000, 25.2, "#3B82F6"));
            list.add(new CategoryStatItem("Hóa đơn & Tiện ích", 1500000, 18.0, "#10B981"));
            list.add(new CategoryStatItem("Di chuyển", 840000, 10.1, "#8B5CF6"));
            list.add(new CategoryStatItem("Giải trí", 700000, 8.3, "#EC4899"));
        } else {
            // Mock data for other months
            list.add(new CategoryStatItem("Thực phẩm", 2800000, 40.0, "#F59E0B"));
            list.add(new CategoryStatItem("Mua sắm", 1500000, 21.4, "#3B82F6"));
            list.add(new CategoryStatItem("Hóa đơn & Tiện ích", 1400000, 20.0, "#10B981"));
            list.add(new CategoryStatItem("Di chuyển", 700000, 10.0, "#8B5CF6"));
            list.add(new CategoryStatItem("Khác", 600000, 8.6, "#6366F1"));
        }
        return list;
    }

    public static double getMockMonthlyIncome(int month, int year) {
        if (month == 10 && year == 2023) return 22500000.0;
        return 18000000.0;
    }

    public static double getMockMonthlyExpense(int month, int year) {
        if (month == 10 && year == 2023) return 8340000.0;
        return 7000000.0;
    }
}
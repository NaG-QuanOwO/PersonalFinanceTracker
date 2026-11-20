package com.example.personalfinancetracker.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.personalfinancetracker.databinding.FragmentDashboardBinding;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DashboardViewModel viewModel;
    private TransactionAdapter adapter;
    private DecimalFormat currencyFormatter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initCurrencyFormatter();
        initRecyclerView();
        initViewModel();
    }

    private void initCurrencyFormatter() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("vi", "VN"));
        symbols.setGroupingSeparator('.');
        currencyFormatter = new DecimalFormat("#,###", symbols);
    }

    private void initRecyclerView() {
        adapter = new TransactionAdapter();
        binding.rvRecentTransactions.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvRecentTransactions.setAdapter(adapter);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        viewModel.getBalance().observe(getViewLifecycleOwner(), balance -> {
            if (balance != null) {
                String formatted = currencyFormatter.format(balance) + " ₫";
                binding.tvBalanceAmount.setText(formatted);
            }
        });

        viewModel.getTotalIncome().observe(getViewLifecycleOwner(), income -> {
            if (income != null) {
                String formatted = "+" + currencyFormatter.format(income) + " ₫";
                binding.tvIncomeAmount.setText(formatted);
            }
        });

        viewModel.getTotalExpense().observe(getViewLifecycleOwner(), expense -> {
            if (expense != null) {
                String formatted = "-" + currencyFormatter.format(expense) + " ₫";
                binding.tvExpenseAmount.setText(formatted);
            }
        });

        viewModel.getRecentTransactions().observe(getViewLifecycleOwner(), transactions -> {
            updateTransactionsList(transactions);
        });
    }

    private void updateTransactionsList(List<TransactionItem> list) {
        if (list == null || list.isEmpty()) {
            binding.rvRecentTransactions.setVisibility(View.GONE);
            binding.layoutEmptyTransactions.setVisibility(View.VISIBLE);
        } else {
            binding.layoutEmptyTransactions.setVisibility(View.GONE);
            binding.rvRecentTransactions.setVisibility(View.VISIBLE);
            adapter.setItems(list);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
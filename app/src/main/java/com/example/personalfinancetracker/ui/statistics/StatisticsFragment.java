package com.example.personalfinancetracker.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.personalfinancetracker.databinding.FragmentStatisticsBinding;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class StatisticsFragment extends Fragment {

    private FragmentStatisticsBinding binding;
    private StatisticsViewModel viewModel;
    private CategoryStatAdapter adapter;
    private DecimalFormat currencyFormatter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initCurrencyFormatter();
        initRecyclerView();
        initViewModel();
        initListeners();
    }

    private void initCurrencyFormatter() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("vi", "VN"));
        symbols.setGroupingSeparator('.');
        currencyFormatter = new DecimalFormat("#,###", symbols);
    }

    private void initRecyclerView() {
        adapter = new CategoryStatAdapter();
        binding.rvCategoryStats.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvCategoryStats.setAdapter(adapter);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(StatisticsViewModel.class);

        viewModel.getMonthYearText().observe(getViewLifecycleOwner(), text -> {
            if (text != null) {
                binding.tvMonthYear.setText(text);
            }
        });

        viewModel.getMonthlyIncome().observe(getViewLifecycleOwner(), income -> {
            if (income != null) {
                String formatted = currencyFormatter.format(income) + " ₫";
                binding.tvMonthlyIncome.setText(formatted);
            }
        });

        viewModel.getMonthlyExpense().observe(getViewLifecycleOwner(), expense -> {
            if (expense != null) {
                String formatted = currencyFormatter.format(expense) + " ₫";
                binding.tvMonthlyExpense.setText(formatted);
            }
        });

        viewModel.getExpenseRatioPercent().observe(getViewLifecycleOwner(), ratio -> {
            if (ratio != null) {
                binding.progressRatioBar.setProgress(ratio);
                String ratioText = getString(com.example.personalfinancetracker.R.string.statistics_ratio_format, ratio);
                binding.tvRatioLabel.setText(ratioText);
            }
        });

        viewModel.getCategoryStats().observe(getViewLifecycleOwner(), stats -> {
            updateCategoryStats(stats);
        });

        viewModel.getHasData().observe(getViewLifecycleOwner(), hasData -> {
            if (Boolean.TRUE.equals(hasData)) {
                binding.cardSummaryComparison.setVisibility(View.VISIBLE);
                binding.cardCategoryBreakdown.setVisibility(View.VISIBLE);
                binding.layoutEmptyStats.setVisibility(View.GONE);
            } else {
                binding.cardSummaryComparison.setVisibility(View.GONE);
                binding.cardCategoryBreakdown.setVisibility(View.GONE);
                binding.layoutEmptyStats.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initListeners() {
        binding.btnPrevMonth.setOnClickListener(v -> viewModel.previousMonth());
        binding.btnNextMonth.setOnClickListener(v -> viewModel.nextMonth());
    }

    private void updateCategoryStats(List<CategoryStatItem> list) {
        if (list == null || list.isEmpty()) {
            adapter.setItems(null);
        } else {
            adapter.setItems(list);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
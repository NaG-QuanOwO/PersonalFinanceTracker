package com.example.personalfinancetracker.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalfinancetracker.R;
import com.example.personalfinancetracker.databinding.ItemTransactionBinding;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private final List<TransactionItem> items = new ArrayList<>();
    private final DecimalFormat formatter;

    public TransactionAdapter() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("vi", "VN"));
        symbols.setGroupingSeparator('.');
        formatter = new DecimalFormat("#,###", symbols);
    }

    public void setItems(List<TransactionItem> newItems) {
        items.clear();
        if (newItems != null) {
            items.addAll(newItems);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTransactionBinding binding = ItemTransactionBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new TransactionViewHolder(binding, formatter);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        private final ItemTransactionBinding binding;
        private final DecimalFormat formatter;

        TransactionViewHolder(ItemTransactionBinding binding, DecimalFormat formatter) {
            super(binding.getRoot());
            this.binding = binding;
            this.formatter = formatter;
        }

        void bind(TransactionItem item) {
            Context context = binding.getRoot().getContext();
            binding.tvTransactionTitle.setText(item.getTitle());

            String subtitle = item.getCategory() + " • " + item.getDate();
            binding.tvTransactionSubtitle.setText(subtitle);

            String formattedAmount = formatter.format(item.getAmount()) + " ₫";
            if (item.isIncome()) {
                binding.tvTransactionAmount.setText("+" + formattedAmount);
                binding.tvTransactionAmount.setTextColor(ContextCompat.getColor(context, R.color.income_green));
                binding.imgCategoryIcon.setImageResource(R.drawable.ic_arrow_upward);
                binding.imgCategoryIcon.setColorFilter(ContextCompat.getColor(context, R.color.income_green));
            } else {
                binding.tvTransactionAmount.setText("-" + formattedAmount);
                binding.tvTransactionAmount.setTextColor(ContextCompat.getColor(context, R.color.expense_red));
                binding.imgCategoryIcon.setImageResource(R.drawable.ic_arrow_downward);
                binding.imgCategoryIcon.setColorFilter(ContextCompat.getColor(context, R.color.expense_red));
            }
        }
    }
}
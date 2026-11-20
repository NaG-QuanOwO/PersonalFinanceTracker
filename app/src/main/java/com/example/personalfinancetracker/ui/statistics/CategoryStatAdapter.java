package com.example.personalfinancetracker.ui.statistics;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalfinancetracker.R;
import com.example.personalfinancetracker.databinding.ItemCategoryStatBinding;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategoryStatAdapter extends RecyclerView.Adapter<CategoryStatAdapter.CategoryViewHolder> {

    private final List<CategoryStatItem> items = new ArrayList<>();
    private final DecimalFormat formatter;

    public CategoryStatAdapter() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("vi", "VN"));
        symbols.setGroupingSeparator('.');
        formatter = new DecimalFormat("#,###", symbols);
    }

    public void setItems(List<CategoryStatItem> newItems) {
        items.clear();
        if (newItems != null) {
            items.addAll(newItems);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryStatBinding binding = ItemCategoryStatBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryViewHolder(binding, formatter);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final ItemCategoryStatBinding binding;
        private final DecimalFormat formatter;

        CategoryViewHolder(ItemCategoryStatBinding binding, DecimalFormat formatter) {
            super(binding.getRoot());
            this.binding = binding;
            this.formatter = formatter;
        }

        void bind(CategoryStatItem item) {
            binding.tvCategoryName.setText(item.getCategoryName());

            String percentStr = String.format(Locale.US, "%.1f%%", item.getPercentage());
            binding.tvCategoryPercentage.setText(percentStr);

            String amountStr = formatter.format(item.getAmount()) + " ₫";
            binding.tvCategoryAmount.setText(amountStr);

            int progressVal = (int) Math.round(item.getPercentage());
            binding.progressCategory.setProgress(progressVal);

            int color = parseColor(item.getColorHex());
            binding.progressCategory.setIndicatorColor(color);

            GradientDrawable dotDrawable = new GradientDrawable();
            dotDrawable.setShape(GradientDrawable.OVAL);
            dotDrawable.setColor(color);
            binding.viewCategoryDot.setBackground(dotDrawable);
        }

        private int parseColor(String hexColor) {
            try {
                if (hexColor != null && !hexColor.isEmpty()) {
                    return Color.parseColor(hexColor);
                }
            } catch (Exception ignored) {
            }
            return ContextCompat.getColor(binding.getRoot().getContext(), R.color.balance_blue);
        }
    }
}
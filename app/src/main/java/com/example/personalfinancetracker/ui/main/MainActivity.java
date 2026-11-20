package com.example.personalfinancetracker.ui.main;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.personalfinancetracker.R;
import com.example.personalfinancetracker.data.model.Category;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private static final String TAG = "coredatabasetest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);


        mainViewModel.getAllCategories().observe(this, categories -> {
            Log.d(TAG, "==== KIỂM TRA DATABASE ====");
            Log.d(TAG, "Số lượng danh mục hiện có: " + categories.size());

            for (Category cat : categories) {
                Log.d(TAG, "Tên: " + cat.getName() + " | Loại: " + cat.getType());
            }
            Log.d(TAG, "===========================");
        });
    }
}
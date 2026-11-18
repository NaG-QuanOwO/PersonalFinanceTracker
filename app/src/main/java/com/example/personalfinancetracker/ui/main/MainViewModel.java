package com.example.personalfinancetracker.ui.main;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.personalfinancetracker.data.model.Category;
import com.example.personalfinancetracker.repository.CategoryRepository;

import java.util.List;
public class MainViewModel extends AndroidViewModel {
    private CategoryRepository repository;
    private LiveData<List<Category>> allCategories;

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new CategoryRepository(application);
        allCategories = repository.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }

}

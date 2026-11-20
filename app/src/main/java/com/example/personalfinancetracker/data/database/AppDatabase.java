package com.example.personalfinancetracker.data.database;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.personalfinancetracker.data.dao.BudgetDao;
import com.example.personalfinancetracker.data.dao.CategoryDao;
import com.example.personalfinancetracker.data.dao.TransactionDao;
import com.example.personalfinancetracker.data.model.Budget;
import com.example.personalfinancetracker.data.model.Category;
import com.example.personalfinancetracker.data.model.Transaction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class, Budget.class, Transaction.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{
    public abstract CategoryDao categoryDao();
    public abstract BudgetDao budgetDao();
    public abstract TransactionDao transactionDao();

    private static volatile AppDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "finance_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallBack)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static final RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                CategoryDao dao = INSTANCE.categoryDao();

                dao.insert(new Category("Ăn uống", "EXPENSE", "ic_food"));
                dao.insert(new Category("Tiền lương", "INCOME", "ic_salary"));
                dao.insert(new Category("Mua sắm", "EXPENSE", "ic_shopping"));
            });
        }
    };
}

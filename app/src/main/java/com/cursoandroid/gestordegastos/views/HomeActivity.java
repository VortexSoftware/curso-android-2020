package com.cursoandroid.gestordegastos.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.cursoandroid.gestordegastos.R;
import com.cursoandroid.gestordegastos.adapters.ExpensesAdapter;
import com.cursoandroid.gestordegastos.databinding.ActivityHomeBinding;
import com.cursoandroid.gestordegastos.helpers.SessionPersistence;
import com.cursoandroid.gestordegastos.models.Expense;
import com.cursoandroid.gestordegastos.models.User;
import com.cursoandroid.gestordegastos.viewModels.HomeViewModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private ExpensesAdapter adapter;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setHomeViewModel(viewModel);
        viewModel.setupUserInfo();
        setupRecyclerView();
        setupObservers();
    }

    private void setupObservers() {
        viewModel.getButonNewExpense().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(HomeActivity.this, "go to new expense", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new ExpensesAdapter(viewModel.generateExpenses());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerExpenses.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.recyclerExpenses.setLayoutManager(linearLayoutManager);
        binding.recyclerExpenses.setAdapter(adapter);
        adapter.setListener(new ExpensesAdapter.ExpensesAdapterInterface() {
            @Override
            public void onItemClick(Expense expense) {
                Toast.makeText(HomeActivity.this, expense.getAccount(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

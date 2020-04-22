package com.cursoandroid.gestordegastos.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import com.cursoandroid.gestordegastos.R;
import com.cursoandroid.gestordegastos.databinding.ActivityDetailExpenseBinding;
import com.cursoandroid.gestordegastos.models.Expense;
import com.cursoandroid.gestordegastos.viewModels.DetailExpenseViewModel;

public class DetailExpenseActivity extends AppCompatActivity {
    private ActivityDetailExpenseBinding binding;
    private DetailExpenseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_expense);
        viewModel = new ViewModelProvider(this).get(DetailExpenseViewModel.class);
        binding.setDetailExpenseViewModel(viewModel);
        if (getIntent().hasExtra("expense")) {
            Expense expense = (Expense) getIntent().getExtras().getSerializable("expense");
            getSupportActionBar().setTitle("Gasto #" + expense.getId());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            viewModel.setExpense(expense);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

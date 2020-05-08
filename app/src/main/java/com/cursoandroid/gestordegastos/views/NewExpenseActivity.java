package com.cursoandroid.gestordegastos.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.widget.Toast;

import com.cursoandroid.gestordegastos.R;
import com.cursoandroid.gestordegastos.databinding.ActivityNewExpenseBinding;
import com.cursoandroid.gestordegastos.models.Account;
import com.cursoandroid.gestordegastos.models.Category;
import com.cursoandroid.gestordegastos.models.Expense;
import com.cursoandroid.gestordegastos.models.Provider;
import com.cursoandroid.gestordegastos.viewModels.NewExpenseViewModel;
import com.cursoandroid.gestordegastos.views.fragments.AccountSelectorFragment;
import com.cursoandroid.gestordegastos.views.fragments.CategorySelectorFragment;
import com.cursoandroid.gestordegastos.views.fragments.ProviderSelectorFragment;

public class NewExpenseActivity extends AppCompatActivity implements AccountSelectorFragment.AccountSelectorFragmentListener,
        CategorySelectorFragment.CategorySelectorFragmentListener, ProviderSelectorFragment.ProviderSelectorFragmentListener {
    private ActivityNewExpenseBinding binding;
    private NewExpenseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_new_expense);
        viewModel = new ViewModelProvider(this).get(NewExpenseViewModel.class);
        binding.setNewExpenseViewModel(viewModel);
        binding.setLifecycleOwner(this);
        getSupportActionBar().setTitle("Nuevo Gasto");
        setupObservers();
    }

    private void setupObservers() {
        viewModel.getAccountPressed().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                getSupportActionBar().setTitle("Seleccione una cuenta");
                AccountSelectorFragment accountSelectorFragment = AccountSelectorFragment.newInstance();
                showFragment(accountSelectorFragment);
            }
        });
        viewModel.getCategoryPressed().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                getSupportActionBar().setTitle("Seleccione una categoría");
                CategorySelectorFragment categorySelectorFragment = CategorySelectorFragment.newInstance();
                showFragment(categorySelectorFragment);
            }
        });

        viewModel.getProviderPressed().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                getSupportActionBar().setTitle("Seleccione un proveedor");
                ProviderSelectorFragment providerSelectorFragment = ProviderSelectorFragment.newInstance(viewModel.getExpense().getValue().getCategory().getId());
                showFragment(providerSelectorFragment);
            }
        });

        viewModel.getNewExpensePressed().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
               if (validateFields()){
                   viewModel.createNewExpense();
               }
            }
        });
        viewModel.getAmount().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                viewModel.getExpense().getValue().setAmount(s);
            }
        });
        viewModel.getDescription().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                viewModel.getExpense().getValue().setDescription(s);
            }
        });
        viewModel.getQuantity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                viewModel.getExpense().getValue().setNumberOfItems(s);
            }
        });
        viewModel.getOnCreateExpenseSuccessMutableLiveData().observe(this,onCreateExpenseSuccess -> {
            showSuccessDialog(onCreateExpenseSuccess.getCreateExpenseResponse().getMessage()).show();
        });
        viewModel.getOnCreateExpenseFailMutableLiveData().observe(this,onCreateExpenseFail -> {
            Toast.makeText(this, onCreateExpenseFail.getError(), Toast.LENGTH_SHORT).show();
        });
    }

    public boolean validateFields(){
        if (viewModel.getExpense().getValue().getAccount()==null){
            Toast.makeText(this, "Seleccione una cuenta", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (viewModel.getExpense().getValue().getCategory()==null){
            Toast.makeText(this, "Seleccione una categoría", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (viewModel.getExpense().getValue().getProvider()==null){
            Toast.makeText(this, "Seleccione un proveedor", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (viewModel.getExpense().getValue().getAmount()==null || viewModel.getExpense().getValue().getAmount().isEmpty()){
            Toast.makeText(this, "Ingrese un monto", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (viewModel.getExpense().getValue().getDescription()==null|| viewModel.getExpense().getValue().getDescription().isEmpty()){
            Toast.makeText(this, "Ingrese una descripción", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ((viewModel.getExpense().getValue().getNumberOfItems()==null|| viewModel.getExpense().getValue().getNumberOfItems().isEmpty())
        && viewModel.getExpense().getValue().getCategory().isNeedsNumberOfItemsInExpenses()){
            Toast.makeText(this, "Ingrese una cantidad", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void showFragment(Fragment fragment){
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,fragment);
        fragmentTransaction.commit();
    }

    public void removeFragmnts(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        for (Fragment fragment : fragmentManager.getFragments()){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }
        getSupportActionBar().setTitle("Nuevo Gasto");
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().isEmpty()){
            super.onBackPressed();
        } else {
            removeFragmnts();
        }

    }


    public AlertDialog.Builder showSuccessDialog(String message){
        return new AlertDialog.Builder(
                this)
                .setTitle("Registro exitoso")
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       finish();
                    }
                });
    }

    @Override
    public void onAccountSelected(Account account) {
        Expense expense = viewModel.getExpense().getValue();
        expense.setAccount(account);
        viewModel.getExpense().setValue(expense);
        removeFragmnts();
    }

    @Override
    public void onCategorySelected(Category category) {
        Expense expense = viewModel.getExpense().getValue();
        expense.setCategory(category);
        viewModel.getExpense().getValue().setProvider(null);
        viewModel.getExpense().setValue(expense);
        removeFragmnts();
    }

    @Override
    public void onProviderSelected(Provider provider) {
        Expense expense = viewModel.getExpense().getValue();
        expense.setProvider(provider);
        viewModel.getExpense().setValue(expense);
        removeFragmnts();
    }
}

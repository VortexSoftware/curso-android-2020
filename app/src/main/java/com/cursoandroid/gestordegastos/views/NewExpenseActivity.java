package com.cursoandroid.gestordegastos.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.widget.Toast;

import com.cursoandroid.gestordegastos.R;
import com.cursoandroid.gestordegastos.databinding.ActivityNewExpenseBinding;
import com.cursoandroid.gestordegastos.models.Account;
import com.cursoandroid.gestordegastos.viewModels.NewExpenseViewModel;
import com.cursoandroid.gestordegastos.views.fragments.AccountSelectorFragment;
import com.cursoandroid.gestordegastos.views.fragments.CategorySelectorFragment;
import com.cursoandroid.gestordegastos.views.fragments.ProviderSelectorFragment;

public class NewExpenseActivity extends AppCompatActivity implements AccountSelectorFragment.AccountSelectorFragmentListener {
    private ActivityNewExpenseBinding binding;
    private NewExpenseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_new_expense);
        viewModel = new ViewModelProvider(this).get(NewExpenseViewModel.class);
        binding.setNewExpenseViewModel(viewModel);
        getSupportActionBar().setTitle("Nuevo Gasto");
        setupObservers();
    }

    private void setupObservers() {
        viewModel.getAccountPressed().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                AccountSelectorFragment accountSelectorFragment = AccountSelectorFragment.newInstance();
                showFragment(accountSelectorFragment);
            }
        });
        viewModel.getCategoryPressed().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                CategorySelectorFragment categorySelectorFragment = CategorySelectorFragment.newInstance();
                showFragment(categorySelectorFragment);
            }
        });

        viewModel.getProviderPressed().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                ProviderSelectorFragment providerSelectorFragment = ProviderSelectorFragment.newInstance();
                showFragment(providerSelectorFragment);
            }
        });

        viewModel.getNewExpensePressed().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(NewExpenseActivity.this, "cargar gasto", Toast.LENGTH_SHORT).show();
            }
        });
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
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().isEmpty()){
            super.onBackPressed();
        } else {
            removeFragmnts();
        }

    }

    @Override
    public void onAccountSelected(Account account) {
        Toast.makeText(this, "account on activity", Toast.LENGTH_SHORT).show();
        removeFragmnts();
    }
}

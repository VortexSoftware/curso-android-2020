package com.cursoandroid.gestordegastos.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cursoandroid.gestordegastos.R;
import com.cursoandroid.gestordegastos.databinding.ActivityLoginBinding;
import com.cursoandroid.gestordegastos.helpers.SessionPersistence;
import com.cursoandroid.gestordegastos.models.User;
import com.cursoandroid.gestordegastos.viewModels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SessionPersistence.getUser() != null) {
            goToHomeActivity();
        } else {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
            viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
            binding.setLoginViewModel(viewModel);
            setupObservers();
        }
    }

    private void setupObservers() {
        viewModel.getUsername().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                viewModel.getUser().setUsername(s);
            }
        });

        viewModel.getPassword().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                viewModel.getUser().setPassword(s);
            }
        });

        viewModel.getLoginButton().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (validateField()) {
                    viewModel.saveUser();
                    goToHomeActivity();
                }
            }
        });
    }

    public String getUserName() {
        return viewModel.getUsername().getValue();
    }


    public void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public String getPassword() {
        return viewModel.getPassword().getValue();
    }

    public boolean validateField() {
        if (getUserName() == null || getUserName().isEmpty()) {
            binding.editTextUserName.requestFocus();
            binding.editTextUserName.setError("Debe completar nombre de usuario");
            return false;
        }
        if (getPassword() == null || getPassword().isEmpty()) {
            binding.editTextPassword.requestFocus();
            binding.editTextPassword.setError("Debe completar contraseña");
            return false;
        }
        return true;
    }
}

package com.cursoandroid.gestordegastos.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandroid.gestordegastos.models.User;
import com.cursoandroid.gestordegastos.repositories.LoginRepository;

public class LoginViewModel extends ViewModel {
    private User user;
    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> passwortd = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginButton = new MutableLiveData<>();
    private LoginRepository loginRepository;

    public LoginRepository getLoginRepository() {
        if (loginRepository == null) loginRepository = new LoginRepository();
        return loginRepository;
    }

    public User getUser() {
        if (user == null) user = new User();
        return user;
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public MutableLiveData<String> getPassword() {
        return passwortd;
    }

    public void onLoginPressed() {
        getLoginButton().setValue(true);
    }

    public MutableLiveData<Boolean> getLoginButton() {
        return loginButton;
    }

    public void saveUser() {
        getLoginRepository().saveUser(getUser());
    }
}

package com.cursoandroid.gestordegastos.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandroid.gestordegastos.models.User;
import com.cursoandroid.gestordegastos.repositories.LoginRepository;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginViewModel extends ViewModel {
    private User user;
    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> passwortd = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginButton = new MutableLiveData<>();
    private MutableLiveData<LoginRepository.OnLoginSuccess> onLoginSuccessData = new MutableLiveData<>();
    private MutableLiveData<LoginRepository.OnLoginFail> onLoginFailData = new MutableLiveData<>();
    private MutableLiveData<Boolean> showOverlay = new MutableLiveData<>();
    private LoginRepository loginRepository;

    public MutableLiveData<Boolean> getShowOverlay() {
        return showOverlay;
    }

    public MutableLiveData<LoginRepository.OnLoginSuccess> getOnLoginSuccessData() {
        return onLoginSuccessData;
    }

    public MutableLiveData<LoginRepository.OnLoginFail> getOnLoginFailData() {
        return onLoginFailData;
    }

    public LoginRepository getLoginRepository() {
        if (loginRepository == null) {
            loginRepository = new LoginRepository();
            setupObserver();
        }
        return loginRepository;
    }

    private void setupObserver() {
        getLoginRepository().getOnLoginSuccesss().subscribe(onLoginSuccess -> {
            getOnLoginSuccessData().setValue(onLoginSuccess);
            getShowOverlay().setValue(false);

        });

        getLoginRepository().getOnLoginFail().subscribe(onLoginFail -> {
                    getOnLoginFailData().setValue(onLoginFail);
                    getShowOverlay().setValue(false);
                }
        );
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

    public void makeLogin() {
        getShowOverlay().setValue(true);
        getLoginRepository().makeLoginToServer(getUser().getUsername(), getUser().getPassword());
    }
}

package com.cursoandroid.gestordegastos.repositories;

import com.cursoandroid.gestordegastos.helpers.SessionPersistence;
import com.cursoandroid.gestordegastos.models.User;
import com.cursoandroid.gestordegastos.network.RestClient;
import com.cursoandroid.gestordegastos.network.responses.LoginResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private PublishSubject<OnLoginSuccess> onLoginSuccesss = PublishSubject.create();
    private PublishSubject<OnLoginFail> onLoginFail = PublishSubject.create();

    public PublishSubject<OnLoginSuccess> getOnLoginSuccesss() {
        return onLoginSuccesss;
    }

    public PublishSubject<OnLoginFail> getOnLoginFail() {
        return onLoginFail;
    }

    public void saveUser(User user) {
        SessionPersistence.saveUser(user);
    }

    public void makeLoginToServer(String username, String password) {
        RestClient.getApiService().makeLogin(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(makeLoginObserver());
    }

    public DisposableSingleObserver<LoginResponse> makeLoginObserver(){
        return new DisposableSingleObserver<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                saveUser(loginResponse.getUser());
                getOnLoginSuccesss().onNext(new OnLoginSuccess(loginResponse));
            }

            @Override
            public void onError(Throwable e) {
                getOnLoginFail().onNext(new OnLoginFail("Ha ocurrido un error"));
            }
        };
    }


    public class OnLoginSuccess {
        private LoginResponse loginResponse;

        public OnLoginSuccess(LoginResponse loginResponse) {
            this.loginResponse = loginResponse;
        }

        public LoginResponse getLoginResponse() {
            return loginResponse;
        }
    }

    public class OnLoginFail {
        private String error;

        public OnLoginFail(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}

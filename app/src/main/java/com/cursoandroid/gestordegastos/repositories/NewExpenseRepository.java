package com.cursoandroid.gestordegastos.repositories;

import com.cursoandroid.gestordegastos.helpers.SessionPersistence;
import com.cursoandroid.gestordegastos.models.Account;
import com.cursoandroid.gestordegastos.models.Category;
import com.cursoandroid.gestordegastos.models.Expense;
import com.cursoandroid.gestordegastos.models.Provider;
import com.cursoandroid.gestordegastos.network.RestClient;
import com.cursoandroid.gestordegastos.network.requests.CreateExpenseRequest;
import com.cursoandroid.gestordegastos.network.responses.CreateExpenseResponse;
import com.cursoandroid.gestordegastos.network.responses.LoginResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class NewExpenseRepository {
    private PublishSubject<OnGetAccountsSuccess> onGetAccountsSuccessData = PublishSubject.create();
    private PublishSubject<OnGetAccountsFail> onGetAccountsFailData = PublishSubject.create();
    private PublishSubject<OnGetCategoriesSuccess> onGetCategoriesSuccessData = PublishSubject.create();
    private PublishSubject<OnGetCategoryFail> onGetCategoriesFailData = PublishSubject.create();
    private PublishSubject<OnGetProvidersSuccess> onGetProvidersSuccessData = PublishSubject.create();
    private PublishSubject<OnGetProvidersFail> onGetProvidersFailData = PublishSubject.create();
    private PublishSubject<OnCreateExpenseSuccess> onCreateExpenseSuccessData = PublishSubject.create();
    private PublishSubject<OnCreateExpenseFail> onCreateExpenseFailData = PublishSubject.create();

    public PublishSubject<OnCreateExpenseSuccess> getOnCreateExpenseSuccessData() {
        return onCreateExpenseSuccessData;
    }

    public PublishSubject<OnCreateExpenseFail> getOnCreateExpenseFailData() {
        return onCreateExpenseFailData;
    }

    public PublishSubject<OnGetProvidersSuccess> getOnGetProvidersSuccessData() {
        return onGetProvidersSuccessData;
    }

    public PublishSubject<OnGetProvidersFail> getOnGetProvidersFailData() {
        return onGetProvidersFailData;
    }

    public PublishSubject<OnGetAccountsSuccess> getOnGetAccountsSuccessData() {
        return onGetAccountsSuccessData;
    }

    public PublishSubject<OnGetAccountsFail> getOnGetAccountsFailData() {
        return onGetAccountsFailData;
    }

    public PublishSubject<OnGetCategoriesSuccess> getOnGetCategoriesSuccessData() {
        return onGetCategoriesSuccessData;
    }

    public PublishSubject<OnGetCategoryFail> getOnGetCategoriesFailData() {
        return onGetCategoriesFailData;
    }

    public void getAccountsFromServer() {
        RestClient.getApiService().getAccounts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(getAccountsObserver());
    }

    public DisposableSingleObserver<ArrayList<Account>> getAccountsObserver() {
        return new DisposableSingleObserver<ArrayList<Account>>() {
            @Override
            public void onSuccess(ArrayList<Account> accounts) {
                getOnGetAccountsSuccessData().onNext(new OnGetAccountsSuccess(accounts));
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof HttpException) {
                    ResponseBody body = ((HttpException) e).response().errorBody();
                    try {
                        JSONObject jObjError = new JSONObject(body.string());
                        LoginResponse loginResponse = new Gson().fromJson(jObjError.toString(), LoginResponse.class);
                        getOnGetAccountsFailData().onNext(new OnGetAccountsFail(loginResponse.getError()));
                    } catch (Exception ex) {
                        getOnGetAccountsFailData().onNext(new OnGetAccountsFail(ex.getMessage()));
                    }
                }
            }
        };
    }

    public void getCategoriesFromServer() {
        RestClient.getApiService().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getCategoriesObserver());
    }

    public DisposableSingleObserver<ArrayList<Category>> getCategoriesObserver() {
        return new DisposableSingleObserver<ArrayList<Category>>() {
            @Override
            public void onSuccess(ArrayList<Category> categories) {
                getOnGetCategoriesSuccessData().onNext(new OnGetCategoriesSuccess(categories));
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof HttpException) {
                    ResponseBody body = ((HttpException) e).response().errorBody();
                    try {
                        JSONObject jObjError = new JSONObject(body.string());
                        LoginResponse loginResponse = new Gson().fromJson(jObjError.toString(), LoginResponse.class);
                        getOnGetCategoriesFailData().onNext(new OnGetCategoryFail(loginResponse.getError()));
                    } catch (Exception ex) {
                        getOnGetCategoriesFailData().onNext(new OnGetCategoryFail(ex.getMessage()));
                    }
                }
            }
        };
    }

    public void getProviderFromServer(String id) {
        RestClient.getApiService().getProviders(Integer.parseInt(id))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(getProviderObserver());
    }


    public DisposableSingleObserver<ArrayList<Provider>> getProviderObserver() {
        return new DisposableSingleObserver<ArrayList<Provider>>() {
            @Override
            public void onSuccess(ArrayList<Provider> providers) {
                getOnGetProvidersSuccessData().onNext(new OnGetProvidersSuccess(providers));
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof HttpException) {
                    ResponseBody body = ((HttpException) e).response().errorBody();
                    try {
                        JSONObject jObjError = new JSONObject(body.string());
                        LoginResponse loginResponse = new Gson().fromJson(jObjError.toString(), LoginResponse.class);
                        getOnGetProvidersFailData().onNext(new OnGetProvidersFail(loginResponse.getError()));
                    } catch (Exception ex) {
                        getOnGetProvidersFailData().onNext(new OnGetProvidersFail(ex.getMessage()));
                    }
                }
            }
        };
    }

    public void createExpense(CreateExpenseRequest createExpenseRequest) {
        RestClient.getApiService().createNewExpense(createExpenseRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(createExpenseObserver());
    }

    public DisposableSingleObserver<CreateExpenseResponse> createExpenseObserver() {
        return new DisposableSingleObserver<CreateExpenseResponse>() {
            @Override
            public void onSuccess(CreateExpenseResponse createExpenseResponse) {
                getOnCreateExpenseSuccessData().onNext(new OnCreateExpenseSuccess(createExpenseResponse));
            }

            @Override
            public void onError(Throwable e) {
                getOnCreateExpenseFailData().onNext(new OnCreateExpenseFail("Error"));
            }
        };
    }

    public void saveExpenseInDatabase(Expense expense) {
        SessionPersistence.saveExpenses(expense);
    }

    public class OnGetAccountsSuccess {
        private ArrayList<Account> accounts;

        public OnGetAccountsSuccess(ArrayList<Account> accounts) {
            this.accounts = accounts;
        }

        public ArrayList<Account> getAccounts() {
            return accounts;
        }
    }

    public class OnGetAccountsFail {
        private String error;

        public OnGetAccountsFail(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }

    public class OnGetCategoriesSuccess {
        private ArrayList<Category> categories;

        public OnGetCategoriesSuccess(ArrayList<Category> categories) {
            this.categories = categories;
        }

        public ArrayList<Category> getCategories() {
            return categories;
        }
    }

    public class OnGetCategoryFail {
        private String error;

        public OnGetCategoryFail(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }

    public class OnGetProvidersSuccess {
        private ArrayList<Provider> providers;

        public OnGetProvidersSuccess(ArrayList<Provider> providers) {
            this.providers = providers;
        }

        public ArrayList<Provider> getProviders() {
            return providers;
        }
    }

    public class OnGetProvidersFail {
        private String error;

        public OnGetProvidersFail(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }

    public class OnCreateExpenseSuccess {
        private CreateExpenseResponse createExpenseResponse;

        public OnCreateExpenseSuccess(CreateExpenseResponse createExpenseResponse) {
            this.createExpenseResponse = createExpenseResponse;
        }

        public CreateExpenseResponse getCreateExpenseResponse() {
            return createExpenseResponse;
        }
    }

    public class OnCreateExpenseFail {
        private String error;

        public OnCreateExpenseFail(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }

}

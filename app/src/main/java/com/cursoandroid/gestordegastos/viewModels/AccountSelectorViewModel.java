package com.cursoandroid.gestordegastos.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandroid.gestordegastos.models.Account;
import com.cursoandroid.gestordegastos.models.Currency;
import com.cursoandroid.gestordegastos.repositories.NewExpenseRepository;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

public class AccountSelectorViewModel extends ViewModel {
    private NewExpenseRepository newExpenseRepository;
    private MutableLiveData<ArrayList<Account>> accounts = new MutableLiveData<>();
    private MutableLiveData<NewExpenseRepository.OnGetAccountsFail> onGetAccountFail = new MutableLiveData<>();

    public NewExpenseRepository getNewExpenseRepository() {
        if (newExpenseRepository == null){
            newExpenseRepository = new NewExpenseRepository();
            setupObservers();
        }
        return newExpenseRepository;
    }

    private void setupObservers() {
        getNewExpenseRepository().getOnGetAccountsSuccessData().subscribe(onGetAccountsSuccess -> {
            getAccounts().setValue(onGetAccountsSuccess.getAccounts());
        });
        getNewExpenseRepository().getOnGetAccountsFailData().subscribe(onGetAccountsFail -> {
            getOnGetAccountFail().setValue(onGetAccountsFail);
        });
    }

    public MutableLiveData<NewExpenseRepository.OnGetAccountsFail> getOnGetAccountFail() {
        return onGetAccountFail;
    }

    public MutableLiveData<ArrayList<Account>> getAccounts() {
        return accounts;
    }

    public void generateAccounts(){
        accounts.setValue(new ArrayList<Account>());
        accounts.getValue().add(new Account("1","Caja chica",new Currency("ARS - Pesos")));
        accounts.getValue().add(new Account("2","Caja grande",new Currency("ARS - Pesos")));
        accounts.getValue().add(new Account("3","Caja fuerte",new Currency("ARS - Pesos")));
        accounts.getValue().add(new Account("4","Caja en dolares",new Currency("ARS - Pesos")));
    }

    public void getAccountsFromServer() {
        getNewExpenseRepository().getAccountsFromServer();
    }
}

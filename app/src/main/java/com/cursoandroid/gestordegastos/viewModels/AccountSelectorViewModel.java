package com.cursoandroid.gestordegastos.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandroid.gestordegastos.models.Account;
import com.cursoandroid.gestordegastos.models.Currency;

import java.util.ArrayList;

public class AccountSelectorViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Account>> accounts = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Account>> getAccounts() {
        generateAccounts();
        return accounts;
    }

    public void generateAccounts(){
        accounts.setValue(new ArrayList<Account>());
        accounts.getValue().add(new Account("1","Caja chica",new Currency("ARS - Pesos")));
        accounts.getValue().add(new Account("2","Caja grande",new Currency("ARS - Pesos")));
        accounts.getValue().add(new Account("3","Caja fuerte",new Currency("ARS - Pesos")));
        accounts.getValue().add(new Account("4","Caja en dolares",new Currency("ARS - Pesos")));
    }
}

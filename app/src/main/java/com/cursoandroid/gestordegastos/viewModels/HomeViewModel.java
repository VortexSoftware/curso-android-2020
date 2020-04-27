package com.cursoandroid.gestordegastos.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandroid.gestordegastos.models.Account;
import com.cursoandroid.gestordegastos.models.Category;
import com.cursoandroid.gestordegastos.models.Currency;
import com.cursoandroid.gestordegastos.models.Expense;
import com.cursoandroid.gestordegastos.models.Provider;
import com.cursoandroid.gestordegastos.models.User;
import com.cursoandroid.gestordegastos.repositories.HomeRepository;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Expense>> expenses = new MutableLiveData<>();
    private MutableLiveData<String> userName = new MutableLiveData<>();
    private MutableLiveData<Boolean> butonNewExpense = new MutableLiveData<>();
    private HomeRepository homeRepository;

    public HomeRepository getHomeRepository() {
        if (homeRepository == null) homeRepository = new HomeRepository();
        return homeRepository;
    }

    public MutableLiveData<ArrayList<Expense>> getExpenses() {
        return expenses;
    }

    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public MutableLiveData<Boolean> getButonNewExpense() {
        return butonNewExpense;
    }

    public void onButonNewExpensePressed() {
        getButonNewExpense().setValue(true);
    }

    public ArrayList<Expense> generateExpenses() {
        return getHomeRepository().getExpensesFromDatabase();
    }

    public void setupUserInfo() {
        User user = getHomeRepository().getUser();
        getUserName().setValue(user.getUsername());
    }
}

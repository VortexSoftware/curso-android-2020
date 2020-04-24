package com.cursoandroid.gestordegastos.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandroid.gestordegastos.models.Expense;

public class NewExpenseViewModel extends ViewModel {
    private MutableLiveData<Expense> expense = new MutableLiveData<>();
    private MutableLiveData<Boolean> accountPressed = new MutableLiveData<>();
    private MutableLiveData<Boolean> categoryPressed = new MutableLiveData<>();
    private MutableLiveData<Boolean> providerPressed = new MutableLiveData<>();
    private MutableLiveData<Boolean> newExpensePressed = new MutableLiveData<>();


    public MutableLiveData<Boolean> getAccountPressed() {
        return accountPressed;
    }

    public MutableLiveData<Boolean> getCategoryPressed() {
        return categoryPressed;
    }

    public MutableLiveData<Boolean> getProviderPressed() {
        return providerPressed;
    }

    public MutableLiveData<Boolean> getNewExpensePressed() {
        return newExpensePressed;
    }

    public MutableLiveData<Expense> getExpense() {
        return expense;
    }


    public void onAccountPressed(){
        getAccountPressed().setValue(true);
    }

    public void onCategoryPressed(){
        getCategoryPressed().setValue(true);
    }

    public void onProviderPressed(){
        getProviderPressed().setValue(true);
    }

    public void onNewExpensePressed(){
        getNewExpensePressed().setValue(true);
    }
}

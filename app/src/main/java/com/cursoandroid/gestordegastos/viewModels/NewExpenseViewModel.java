package com.cursoandroid.gestordegastos.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandroid.gestordegastos.models.Expense;
import com.cursoandroid.gestordegastos.repositories.NewExpenseRepository;

public class NewExpenseViewModel extends ViewModel {
    private NewExpenseRepository newExpenseRepository;
    private MutableLiveData<Expense> expense = new MutableLiveData<>();
    private MutableLiveData<Boolean> accountPressed = new MutableLiveData<>();
    private MutableLiveData<Boolean> categoryPressed = new MutableLiveData<>();
    private MutableLiveData<Boolean> providerPressed = new MutableLiveData<>();
    private MutableLiveData<Boolean> newExpensePressed = new MutableLiveData<>();
    private MutableLiveData<String> amount = new MutableLiveData<>();
    private MutableLiveData<String> description = new MutableLiveData<>();
    private MutableLiveData<String> quantity = new MutableLiveData<>();

    public NewExpenseRepository getNewExpenseRepository() {
        if (newExpenseRepository == null) newExpenseRepository = new NewExpenseRepository();
        return newExpenseRepository;
    }

    public MutableLiveData<String> getAmount() {
        return amount;
    }

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public MutableLiveData<String> getQuantity() {
        return quantity;
    }

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
        if (expense.getValue()==null) expense.setValue(new Expense());
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

    public void saveExpense() {
        getNewExpenseRepository().saveExpenseInDatabase(getExpense().getValue());
    }
}

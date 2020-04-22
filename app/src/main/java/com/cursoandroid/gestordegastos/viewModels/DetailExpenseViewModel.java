package com.cursoandroid.gestordegastos.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandroid.gestordegastos.models.Expense;

public class DetailExpenseViewModel extends ViewModel {
    private MutableLiveData<Expense> expense = new MutableLiveData<>();

    public MutableLiveData<Expense> getExpense() {
        return expense;
    }

    public void setExpense(Expense expense){
        getExpense().setValue(expense);
    }
}

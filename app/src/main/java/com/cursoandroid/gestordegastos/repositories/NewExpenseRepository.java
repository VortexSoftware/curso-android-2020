package com.cursoandroid.gestordegastos.repositories;

import com.cursoandroid.gestordegastos.helpers.SessionPersistence;
import com.cursoandroid.gestordegastos.models.Expense;

public class NewExpenseRepository {

    public void saveExpenseInDatabase(Expense expense){
        SessionPersistence.saveExpenses(expense);
    }
}

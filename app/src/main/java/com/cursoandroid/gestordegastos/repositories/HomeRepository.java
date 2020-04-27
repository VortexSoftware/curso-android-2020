package com.cursoandroid.gestordegastos.repositories;

import com.cursoandroid.gestordegastos.helpers.SessionPersistence;
import com.cursoandroid.gestordegastos.models.Expense;
import com.cursoandroid.gestordegastos.models.User;

import java.util.ArrayList;

public class HomeRepository {

    public User getUser() {
        return SessionPersistence.getUser();
    }

    public ArrayList<Expense> getExpensesFromDatabase(){
        return SessionPersistence.getSavedExpenses();
    }
}

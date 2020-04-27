package com.cursoandroid.gestordegastos.helpers;

import com.cursoandroid.gestordegastos.models.Account;
import com.cursoandroid.gestordegastos.models.Category;
import com.cursoandroid.gestordegastos.models.Currency;
import com.cursoandroid.gestordegastos.models.Expense;
import com.cursoandroid.gestordegastos.models.Provider;
import com.cursoandroid.gestordegastos.models.User;

import java.util.ArrayList;

import io.paperdb.Paper;

public class SessionPersistence {

    public static void saveUser(User user){
        Paper.book().write("user",user);
    }

    public static User getUser(){
        return Paper.book().read("user");
    }

    public static void deleteUser(){
        Paper.book().delete("user");
    }

    public static void deleteExpenses(){
        Paper.book().delete("expenses");
    }

    public static void saveExpenses(Expense expense){
        ArrayList<Expense> expenses = getSavedExpenses();
        expenses.add(expense);
        Paper.book().write("expenses",expenses);
    }

    public static ArrayList<Expense> getSavedExpenses() {
        if (!Paper.book().contains("expenses")){
            Paper.book().write("expenses",generateExpenses());
        }
        return Paper.book().read("expenses");
    }

    private static ArrayList<Expense> generateExpenses() {
        Account account1 = new Account("1","caja chica",new Currency("ARS - PESOS"));
        Account account2 = new Account("2","caja fuerte",new Currency("ARS - PESOS"));
        Account account3 = new Account("3","caja en dolares",new Currency("USD - DOLAR"));

        Category category1 = new Category("1","Comida");
        Category category2 = new Category("2","Limpieza");

        Provider provider1 = new Provider("1","proveedor 1");
        Provider provider2 = new Provider("2","proveedor 2");
        Provider provider3 = new Provider("3","proveedor 3");

        ArrayList<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("1","$489", account1, provider1, "Martes 15 de Abril de 2020", category2, "5", "Esto es una descripción"));
        expenses.add(new Expense("2","$139", account3, provider2, "Martes 15 de Abril de 2020", category1, "0", "Esto es una descripción"));
        expenses.add(new Expense("3","$289", account1, provider2, "Martes 15 de Abril de 2020", category1, "5", "Esto es una descripción 2"));
        expenses.add(new Expense("4","$589", account2, provider3, "Martes 15 de Abril de 2020", category2, "6", "Esto es una descripción"));
        expenses.add(new Expense("5","$589", account3, provider2, "Martes 15 de Abril de 2020", category2, "1", "Esto es una descripción"));
        return expenses;
    }

}

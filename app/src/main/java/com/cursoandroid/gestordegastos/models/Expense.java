package com.cursoandroid.gestordegastos.models;

public class Expense {
    private String amount;
    private String account;
    private String provider;
    private String date;


    public Expense(String amount, String account, String provider, String date) {
        this.amount = amount;
        this.account = account;
        this.provider = provider;
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

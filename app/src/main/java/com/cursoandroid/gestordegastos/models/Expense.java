package com.cursoandroid.gestordegastos.models;

import java.io.Serializable;

public class Expense implements Serializable {
    private String id;
    private String amount;
    private Account account;
    private Provider provider;
    private String date;
    private Category category;
    private String itemQuantity;
    private String description;


    public Expense(String id, String amount, Account account, Provider provider, String date, Category category, String itemQuantity, String description) {
        this.amount = amount;
        this.date = date;
        this.itemQuantity = itemQuantity;
        this.description = description;
        this.id = id;
        this.account = account;
        this.provider = provider;
        this.category = category;
    }

    public Expense() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

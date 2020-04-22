package com.cursoandroid.gestordegastos.models;

import java.io.Serializable;

public class Expense implements Serializable {
    private String id;
    private String amount;
    private String account;
    private String provider;
    private String date;
    private String category;
    private String currency;
    private String itemQuantity;
    private String description;


    public Expense(String id, String amount, String account, String provider, String date, String category, String currency, String itemQuantity, String description) {
        this.amount = amount;
        this.account = account;
        this.provider = provider;
        this.date = date;
        this.category = category;
        this.currency = currency;
        this.itemQuantity = itemQuantity;
        this.description = description;
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

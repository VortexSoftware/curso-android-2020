package com.cursoandroid.gestordegastos.models;

import com.cursoandroid.gestordegastos.utils.DateUtils;

import java.io.Serializable;

public class Expense implements Serializable {
    private String id;
    private String amount;
    private Account account;
    private Provider provider;
    private String createdAt;
    private Category category;
    private String numberOfItems;
    private String description;


    public Expense(String id, String amount, Account account, Provider provider, String date, Category category, String itemQuantity, String description) {
        this.amount = amount;
        this.createdAt = date;
        this.numberOfItems = itemQuantity;
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
        return "$"+amount;
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

    public String getCreatedAt() {
        return DateUtils.stringToString(createdAt,DateUtils.SERVER_DATE,DateUtils.FULL_DATE);
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(String numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public boolean isNumberOfItemsEmptyOrNull(){
        return numberOfItems == null || numberOfItems.isEmpty();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

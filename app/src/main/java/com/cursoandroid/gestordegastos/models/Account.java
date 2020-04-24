package com.cursoandroid.gestordegastos.models;

public class Account {
    private String id;
    private String name;
    private Currency currency;

    public Account(String id, String name, Currency currency) {
        this.id = id;
        this.name = name;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}

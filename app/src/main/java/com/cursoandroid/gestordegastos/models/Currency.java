package com.cursoandroid.gestordegastos.models;

import java.io.Serializable;

public class Currency implements Serializable {
    private String id;
    private String name;
    private String symbol;

    public Currency(String symbol) {
        this.symbol = symbol;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        symbol = symbol;
    }
}

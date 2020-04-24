package com.cursoandroid.gestordegastos.models;

public class Currency {
    private String id;
    private String name;
    private String Symbol;

    public Currency(String symbol) {
        Symbol = symbol;
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
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }
}

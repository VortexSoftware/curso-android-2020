package com.cursoandroid.gestordegastos.network.requests;

public class CreateExpenseRequest {
    private String description;
    private Double amount;
    private Integer accountId;
    private Integer categoryId;
    private Integer providerId;

    public CreateExpenseRequest(String description, Double amount, Integer accountId, Integer categoryId, Integer providerId) {
        this.description = description;
        this.amount = amount;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.providerId = providerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }
}

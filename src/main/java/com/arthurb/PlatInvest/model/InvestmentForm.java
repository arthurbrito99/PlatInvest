package com.arthurb.PlatInvest.model;

import javax.validation.constraints.NotNull;

public class InvestmentForm {
    @NotNull
    private Double value;
    @NotNull
    private String cpf;
    @NotNull
    private Integer quantity;

    public InvestmentForm() {
    }

    public InvestmentForm(Double value, String cpf, Integer quantity) {
        this.value = value;
        this.cpf = cpf;
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

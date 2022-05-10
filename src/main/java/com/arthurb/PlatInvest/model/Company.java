package com.arthurb.PlatInvest.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "stock")
    @NotEmpty
    private String stock;
    @Column(name = "ticker")
    @NotEmpty
    private String ticker;
    @Column(name = "price")
    @NotNull
    private Double price;
    @Column(name = "status")
    @NotNull
    private Boolean status;

    public Company() {
    }

    public Company(String stock, String ticker, Double price, Boolean status) {
        this.stock = stock;
        this.ticker = ticker;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}




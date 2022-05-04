package com.arthurb.PlatInvest.model;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "stock")
    private String stock;
    @Column(name = "ticker")
    private String ticker;
    @Column(name = "price")
    private Double price;
    @Column(name = "status")
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




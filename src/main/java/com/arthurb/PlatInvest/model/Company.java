package com.arthurb.PlatInvest.model;

import javax.persistence.*;

@Entity
@Table(name = "COMPANIES")

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "stock")
    private String stock;
    @Column(name = "ticker")
    private String ticker;
    @Column(name = "price")
    private double price;
    @Column(name = "status")
    private boolean status;

    public Company() {
    }

    public Company(String stock, String ticker, double price, boolean status) {
        this.stock = stock;
        this.ticker = ticker;
        this.price = price;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}

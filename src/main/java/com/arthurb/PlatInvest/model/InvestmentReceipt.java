package com.arthurb.PlatInvest.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "investment_receipt")
public class InvestmentReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investmentRecord_id")
    private InvestmentRecord investmentRecord;

    @OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "ID")
    private Company company;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private Double total;

    public InvestmentReceipt() {
    }

    public InvestmentReceipt(InvestmentRecord investmentRecord, Company company, Integer quantity, Double total) {
        this.investmentRecord = investmentRecord;
        this.company = company;
        this.quantity = quantity;
        this.total = total;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}

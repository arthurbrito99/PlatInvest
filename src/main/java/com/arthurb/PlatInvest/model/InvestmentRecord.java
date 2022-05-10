package com.arthurb.PlatInvest.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "investment_record")
public class InvestmentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "investmentRecord", cascade = CascadeType.ALL)
    private List<InvestmentReceipt> receipt = new ArrayList<>();

    @Column(name = "investorCpf")
    private String investorCpf;

    @Column(name = "total")
    private Double total;

    @Column(name = "change")
    private Double change;

    public InvestmentRecord(){
    }

    public InvestmentRecord(List<InvestmentReceipt> receipt, Double total, Double change) {
        this.receipt = receipt;
        this.total = total;
        this.change = change;
    }

    public List<InvestmentReceipt> getReceipt() {
        return receipt;
    }

    public void setReceipt(List<InvestmentReceipt> receipt) {
        this.receipt = receipt;
    }

    public String getInvestorCpf() { return investorCpf; }

    public void setInvestorCpf(String investorCpf) { this.investorCpf = investorCpf; }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }
}
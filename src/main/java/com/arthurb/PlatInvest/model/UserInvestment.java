package com.arthurb.PlatInvest.model;

import javax.persistence.*;

@Entity
@Table(name = "user_investments")
public class UserInvestment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "ID")
    private Company company;

    @Column(name = "quantity")
    private Integer quantity;

    public UserInvestment() {
    }

    public UserInvestment(Company company, Integer quantity) {
        this.company = company;
        this.quantity = quantity;
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
}

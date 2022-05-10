package com.arthurb.PlatInvest.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "cpf")
    @NotNull
    private String cpf;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserInvestment> investment = new ArrayList<>();

    public User() {
    }

    public User(String name, String cpf, List<UserInvestment> investment) {
        this.name = name;
        this.cpf = cpf;
        this.investment = investment;
    }

    public Long getId() {
        return id;
    }

    public Long setId(Long id) { return this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<UserInvestment> getInvestment() {
        return investment;
    }

    public void setInvestment(List<UserInvestment> investment) {
        this.investment = investment;
    }

    public User createInvestment(Company company, Integer quantity) {
        this.investment.add(new UserInvestment(company, quantity, this));
        return this;
    }

    public User updateInvestment(Company company, Integer quantity) {
        int index = this.investment.indexOf(this.investment.stream().filter(investment -> investment.getCompany().equals(company)).findFirst().get());
        this.investment.get(index).setQuantity(quantity);
        this.investment.get(index).setInvestedValue(quantity * company.getPrice());
        return this;
    }
}

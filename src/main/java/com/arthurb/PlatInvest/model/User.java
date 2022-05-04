package com.arthurb.PlatInvest.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;
//    @Column(name = "investment")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "ID")
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
}

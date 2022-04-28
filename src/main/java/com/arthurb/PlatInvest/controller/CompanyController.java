package com.arthurb.PlatInvest.controller;

import com.arthurb.PlatInvest.model.Company;
import com.arthurb.PlatInvest.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;
    @GetMapping("/company")
    public ResponseEntity<List<Company>> getAllCompany(){
        return ResponseEntity.ok(companyRepository.findAll());
    }
}

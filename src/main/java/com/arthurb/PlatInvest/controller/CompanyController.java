package com.arthurb.PlatInvest.controller;

import com.arthurb.PlatInvest.model.Company;
import com.arthurb.PlatInvest.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    @PostMapping("/company/create")
    public ResponseEntity<Company> createCompany(@Validated @RequestBody Company company){
        try {
            return ResponseEntity.ok(companyRepository.save(company));
        }   catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/company/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable("id") Long id, @RequestBody Company company){
        Company auxCompany = companyRepository.findById(id).orElse(null);
        if(auxCompany != null){
            auxCompany.setStock(company.getStock());
            auxCompany.setTicker(company.getTicker());
            auxCompany.setPrice(company.getPrice());
            auxCompany.setStatus(company.getStatus());
            return ResponseEntity.ok(companyRepository.save(auxCompany));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable("id") Long id){
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/company")
    public ResponseEntity<List<Company>> getAllCompany(){
        return ResponseEntity.ok(companyRepository.findAll());
    }

    @GetMapping("/company/status")
    public ResponseEntity<List<Company>> getAllCompanyByStatus(@RequestParam("status") Boolean status){
        return ResponseEntity.ok(companyRepository.findByStatus(status));
    }
}

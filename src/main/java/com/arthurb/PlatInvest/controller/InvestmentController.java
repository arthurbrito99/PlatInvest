package com.arthurb.PlatInvest.controller;

import com.arthurb.PlatInvest.model.InvestmentForm;
import com.arthurb.PlatInvest.model.InvestmentRecord;
import com.arthurb.PlatInvest.model.User;
import com.arthurb.PlatInvest.repository.InvestmentRepository;
import com.arthurb.PlatInvest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class InvestmentController {

    @Autowired
    InvestmentRepository investmentRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/investment")
    public ResponseEntity<List<InvestmentRecord>> getAllInvestmentRecord(){
        return ResponseEntity.ok(investmentRepository.findAll());
    }



}

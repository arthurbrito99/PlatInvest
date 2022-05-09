package com.arthurb.PlatInvest.controller;

import com.arthurb.PlatInvest.model.*;
import com.arthurb.PlatInvest.repository.CompanyRepository;
import com.arthurb.PlatInvest.repository.InvestmentRepository;
import com.arthurb.PlatInvest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class InvestmentController {

    @Autowired
    InvestmentRepository investmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping("/investment")
    public ResponseEntity<List<InvestmentRecord>> getAllInvestmentRecord(){
        return ResponseEntity.ok(investmentRepository.findAll());
    }

    @PostMapping("/investment")
    public ResponseEntity<InvestmentRecord> invest(@RequestBody InvestmentForm investmentForm){

        User actualUser = userRepository.findByCpf(investmentForm.getCpf());
        List<Company> activeCompanies = companyRepository.findByStatus(true);
        Double remainingValue = investmentForm.getValue();
        List<Company> companiesToInvest = new ArrayList<>();
        List<UserInvestment> userInvestments = actualUser.getInvestment();

        InvestmentRecord investmentRecord = new InvestmentRecord();

        if(investmentForm.getQuantity() >= activeCompanies.size()) {
            companiesToInvest.addAll(activeCompanies);

            companiesToInvest.sort(Comparator.comparing(Company::getPrice));
        } else {
            for (Company company : activeCompanies) {
                UserInvestment investment = userInvestments.stream().filter(investmentItem -> investmentItem.getCompany().equals(company)).findFirst().orElse(null);
                if (investment == null) {
                    companiesToInvest.add(company);
                }
                if (companiesToInvest.size() == investmentForm.getQuantity()) {
                    break;
                }
            }

            if (companiesToInvest.size() < investmentForm.getQuantity()) {
                userInvestments.sort(Comparator.comparing(UserInvestment::getInvestedValue));

                for (UserInvestment investment : userInvestments) {
                    companiesToInvest.add(investment.getCompany());
                    if (companiesToInvest.size() == investmentForm.getQuantity()) {
                        break;
                    }
                }
            }
        }


        for (int i = 0; i < companiesToInvest.size(); i++) {

            Double investmentPerCompany = remainingValue / (companiesToInvest.size() - i);
            Company actualCompany = companiesToInvest.get(i);

            UserInvestment investment = userInvestments.stream().filter(investmentItem -> investmentItem.getCompany().equals(actualCompany)).findFirst().orElse(null);

            Integer quantityToInvest = (Integer) (int) (Math.floor(investmentPerCompany / actualCompany.getPrice()));

            double total = (actualCompany.getPrice() * quantityToInvest);

            if (investment != null) {
                Integer quantity = investment.getQuantity() + quantityToInvest;
                userRepository.save(actualUser.updateInvestment(actualCompany, quantity));
                investmentRecord.getReceipt().add(new InvestmentReceipt(investmentRecord, actualCompany, quantityToInvest, total));
                remainingValue -= total;
            } else {
                Integer investmentQuantity = (int) Math.floor(investmentPerCompany / actualCompany.getPrice());
                userRepository.save(actualUser.createInvestment(actualCompany, investmentQuantity));
                investmentRecord.getReceipt().add(new InvestmentReceipt(investmentRecord, actualCompany, quantityToInvest, total));
                remainingValue -= total;
            }
        }

        investmentRecord.setTotal(investmentForm.getValue()-remainingValue);
        investmentRecord.setChange(remainingValue);
        investmentRepository.save(investmentRecord);

        return ResponseEntity.ok(investmentRecord);
    }

}

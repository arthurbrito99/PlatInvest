package com.arthurb.PlatInvest;

import com.arthurb.PlatInvest.controller.InvestmentController;
import com.arthurb.PlatInvest.controller.UserController;
import com.arthurb.PlatInvest.model.Company;
import com.arthurb.PlatInvest.model.User;
import com.arthurb.PlatInvest.repository.CompanyRepository;
import com.arthurb.PlatInvest.repository.InvestmentRepository;
import com.arthurb.PlatInvest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(InvestmentController.class)
public class InvestmentControllerTests {
    @MockBean
    InvestmentRepository investmentRepository;
    @MockBean
    UserRepository userRepository;
    @MockBean
    CompanyRepository companyRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testInvest() throws Exception {
        User user = new User("Carlos", "28557826605", new ArrayList<>());
        Mockito.when(userRepository.findByCpf("28557826605")).thenReturn(user);

        Company company1 = new Company("Inter", "INT", 70.00, true);
        Company company2 = new Company("Magazine Luiza", "MGL", 18.50, true);
        Company company3 = new Company("Sulamérica", "SULA", 28.25, false);

        ArrayList<Company> companies = new ArrayList<>(Arrays.asList(company1, company2, company3));
        ArrayList<Company> activeCompanies = (ArrayList<Company>) companies.stream()
                .filter(Company::getStatus).collect(Collectors.toList());
        Mockito.when(companyRepository.findByStatus(true)).thenReturn(activeCompanies);

        mockMvc.perform(post("/api/investment")
                        .contentType("application/json")
                        .content("{\"value\": 200.00,\"cpf\":\"28557826605\", \"quantity\": 2}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.investorCpf").value("28557826605"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.receipt.size()").value(2));
    }
}

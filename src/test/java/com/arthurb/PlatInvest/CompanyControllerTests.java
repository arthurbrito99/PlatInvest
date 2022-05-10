package com.arthurb.PlatInvest;

import com.arthurb.PlatInvest.controller.CompanyController;
import com.arthurb.PlatInvest.model.Company;
import com.arthurb.PlatInvest.model.User;
import com.arthurb.PlatInvest.repository.CompanyRepository;
import com.arthurb.PlatInvest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTests {

    @MockBean
    CompanyRepository companyRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testCreateCompany() throws Exception {
        Company company = new Company("Inter", "INT", 70.00, true);

        Mockito.when(companyRepository.save(company)).thenReturn(company);

        mockMvc.perform(post("/api/company/create")
                        .contentType("application/json")
                        .content("{\"stock\":\"Inter\",\"ticker\":\"INT\",\"price\":70.00,\"status\":true}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAndUpdateCompany() throws Exception {
        Company company = new Company("Inter", "INT", 70.00, true);
        Company companyToUpdate = company;
        company.setId(1L);
        companyToUpdate.setTicker("INTR");
        Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        Mockito.when(companyRepository.save(companyToUpdate)).thenReturn(companyToUpdate);

        mockMvc.perform(put("/api/company/" + 1L)
                        .contentType(MediaType.valueOf("application/json"))
                        .content("{\"stock\":\"Inter\",\"ticker\":\"INT\",\"price\":70.00,\"status\":true}"))
        .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCompany() throws Exception {
        Company company = new Company("Inter", "INT", 70.00, true);
        company.setId(1L);
        Mockito.when(companyRepository.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/company/" + 1L)
                        .contentType(MediaType.valueOf("application/json")))
        .andExpect(status().isOk());
    }

    @Test void GetAllCompanies() throws Exception {
        Company company1 = new Company("Inter", "INT", 70.00, true);
        Company company2 = new Company("Magazine Luiza", "MGL", 18.50, true);

        ArrayList<Company> companies = new ArrayList<>(Arrays.asList(company1, company2));

        Mockito.when(companyRepository.findAll()).thenReturn(companies);

        mockMvc.perform(get("/api/company")
                .contentType(MediaType.valueOf("application/json")))
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"stock\":\"Inter\",\"ticker\":\"INT\",\"price\":70.00,\"status\":true}," + "{\"stock\":\"Magazine Luiza\",\"ticker\":\"MGL\",\"price\":18.50,\"status\":true}]"));
    }

    @Test void GetAllActiveCompanies() throws Exception {
        Company company1 = new Company("Inter", "INT", 70.00, true);
        Company company2 = new Company("Magazine Luiza", "MGL", 18.50, true);
        Company company3 = new Company("Sulamérica", "SULA", 28.25, false);

        ArrayList<Company> companies = new ArrayList<>(Arrays.asList(company1, company2, company3));
        ArrayList<Company> activeCompanies = (ArrayList<Company>) companies.stream()
                .filter(Company::getStatus).collect(Collectors.toList());
        Mockito.when(companyRepository.findByStatus(true)).thenReturn(activeCompanies);

        mockMvc.perform(get("/api/company/status")
                .contentType(MediaType.valueOf("application/json"))
                .param("status", "true"))
            .andExpect(status().isOk())
            .andExpect(content().json(
                    "[{\"stock\":\"Inter\",\"ticker\":\"INT\",\"price\":70.00,\"status\":true},"
                            + "{\"stock\":\"Magazine Luiza\",\"ticker\":\"MGL\",\"price\":18.50,\"status\":true}]"));
    }
}

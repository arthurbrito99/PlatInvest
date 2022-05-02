package com.arthurb.PlatInvest.repository;

import com.arthurb.PlatInvest.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByStatus(boolean status);
}

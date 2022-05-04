package com.arthurb.PlatInvest.repository;

import com.arthurb.PlatInvest.model.InvestmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<InvestmentRecord, Long> {
}

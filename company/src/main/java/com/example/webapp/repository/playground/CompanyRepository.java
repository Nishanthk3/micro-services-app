package com.example.webapp.repository.playground;

import com.example.webapp.entity.playground.CompanyDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyDO, Long> {
    Collection<CompanyDO> findByCompanyName(String companyName);
}

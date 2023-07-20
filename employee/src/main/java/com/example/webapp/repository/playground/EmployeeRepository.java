package com.example.webapp.repository.playground;

import com.example.webapp.entity.playground.EmployeeDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDO, Long> {
    Collection<EmployeeDO> findByEmployeeName(String employeeName);
}

package org.webbasedapplication.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.webbasedapplication.entity.Employee;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByNameIgnoreCase(String name);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE employee AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}

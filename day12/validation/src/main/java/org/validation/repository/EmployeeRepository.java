package org.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.validation.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}

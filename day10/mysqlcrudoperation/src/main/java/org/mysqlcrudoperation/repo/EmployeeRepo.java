package org.mysqlcrudoperation.repo;

import org.mysqlcrudoperation.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    @Modifying
    @Transactional
    //MySQL Query to delete all records and reset ID from the employee table
    //@Query(value = "TRUNCATE employee", nativeQuery = true)
    //POSTGRESQL Query to delete all records and reset ID from the employee table
    @Query(value = "TRUNCATE employee RESTART IDENTITY ", nativeQuery = true)
    void deleteAllEmployees();
}

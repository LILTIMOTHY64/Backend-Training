package crudoperation.repo;

import crudoperation.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRepo extends JpaRepository<Employee,Integer> {
}

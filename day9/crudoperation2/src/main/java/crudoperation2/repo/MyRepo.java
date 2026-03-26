package crudoperation2.repo;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import crudoperation2.entity.Employee;
public interface MyRepo extends JpaRepository<Employee, Integer> {
    // GET ALL (Native)
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployees();

    // GET BY ID
    @Query(value = "SELECT * FROM employee WHERE id = ?1", nativeQuery = true)
    Employee getEmployeeById(int id);

    //  UPDATE
    @Modifying
    @Transactional
    @Query(value = "UPDATE employee SET name = ?1, salary = ?2 WHERE id = ?3", nativeQuery = true)
    int updateEmployee(String name, double salary, int id);

    // DELETE
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employee WHERE id = ?1", nativeQuery = true)
    int deleteEmployee(int id);
}

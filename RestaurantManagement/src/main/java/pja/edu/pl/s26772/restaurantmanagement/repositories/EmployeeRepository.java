package pja.edu.pl.s26772.restaurantmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pja.edu.pl.s26772.restaurantmanagement.models.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    @Query("SELECT e FROM Employee e")
    List<Employee> getAllEmployees();
    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId")
    List<Employee> findByDepartmentId(@Param("departmentId") Long departmentId);
    @Query("SELECT e.supervisor FROM Employee e WHERE e.id = :employeeId")
    Optional<Employee> findSupervisorByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT e FROM Employee e WHERE e.supervisor = :supervisor")
    List<Employee> getSupervisedEmployees(@Param("supervisor") Employee supervisor);
}

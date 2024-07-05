package pja.edu.pl.s26772.restaurantmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pja.edu.pl.s26772.restaurantmanagement.models.Category;
import pja.edu.pl.s26772.restaurantmanagement.models.Department;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department,Long> {
    @Query("SELECT d FROM Department d")
    List<Department> getAllDepartments();

    @Query("SELECT d FROM Department d WHERE d.name = :deptName")
    Optional<Department> getDepartmentByName(@Param("deptName") String deptName);
}

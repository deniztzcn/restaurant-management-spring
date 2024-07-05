package pja.edu.pl.s26772.restaurantmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pja.edu.pl.s26772.restaurantmanagement.models.Customer;
import pja.edu.pl.s26772.restaurantmanagement.models.Tables;

import java.util.List;
import java.util.Optional;

public interface TableRepository extends CrudRepository<Tables,Long> {
    @Query("SELECT t FROM Tables t JOIN t.visits v WHERE v.date = CURRENT_DATE AND v.endTime IS NULL")
    List<Tables> getAllTables();

    @Query("SELECT v.customer FROM Tables t JOIN t.visits v WHERE t.id = :tableId AND v.endTime IS NULL")
    Optional<Customer> getCurrentCustomer(@Param("tableId") Long tableId);
}

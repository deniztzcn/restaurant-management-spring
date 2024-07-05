package pja.edu.pl.s26772.restaurantmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pja.edu.pl.s26772.restaurantmanagement.models.MenuItem;
import pja.edu.pl.s26772.restaurantmanagement.models.Orders;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Orders,Long> {
    @Query("SELECT o FROM Orders o " +
            "WHERE o.deliveredTime IS NULL AND " +
            "o.id.visit IN (SELECT v FROM Visit v WHERE v.endTime IS NULL)")
    List<Orders> findUndeliveredOrdersForCurrentVisits();
    @Query("SELECT o FROM Orders o WHERE o.id.visit.tables.id = :tableId " +
            "AND o.deliveredTime IS NULL")
    List<Orders> getUndeliveredOrdersForGivenTable(@Param("tableId") Long tableId);

    @Query("SELECT o FROM Orders o WHERE o.id.visit.tables.id = :tableId AND o.id.visit.endTime IS NULL")
    List<Orders> getOrdersByTableId(@Param("tableId") Long tableId);
}

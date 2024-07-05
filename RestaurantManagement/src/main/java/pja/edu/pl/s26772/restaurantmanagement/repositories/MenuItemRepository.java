package pja.edu.pl.s26772.restaurantmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pja.edu.pl.s26772.restaurantmanagement.models.MenuItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    @Query("SELECT m FROM MenuItem m WHERE m.category.id = :categoryId")
    List<MenuItem> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT m FROM MenuItem m")
    List<MenuItem> getAllMenuItems();

    @Query("SELECT m FROM MenuItem m WHERE m.name = :itemName")
    Optional<MenuItem> getMenuItemByName(@Param("itemName") String itemName);

    @Query("SELECT o.id.menuItem as menuItem, SUM(o.quantity) as totalQuantity " +
            "FROM Orders o " +
            "WHERE o.id.visit.date = :date " +
            "GROUP BY o.id.menuItem " +
            "ORDER BY SUM(o.quantity) DESC")
    List<Object[]> findMostOrderedMenuItemAtDate(@Param("date") LocalDate date);
}

package pja.edu.pl.s26772.restaurantmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pja.edu.pl.s26772.restaurantmanagement.models.SupplyProduct;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SupplyProductRepository extends CrudRepository<SupplyProduct,Long> {
    @Query("SELECT sp FROM SupplyProduct sp")
    List<SupplyProduct> getAllDeliveries();

    @Query("SELECT sp FROM SupplyProduct sp WHERE sp.product.name = :productName")
    List<SupplyProduct> getDeliveriesByProductName(@Param("productName") String productName);

    @Query("SELECT sp FROM SupplyProduct sp " +
            "WHERE sp.deliveryDate = (SELECT MAX(sp2.deliveryDate) FROM SupplyProduct sp2 WHERE sp2.product.name = :productName) " +
            "AND sp.product.id = :productId")
    Optional<SupplyProduct> getLastDeliveryByProductName(@Param("productName") String productName);
}

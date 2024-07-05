package pja.edu.pl.s26772.restaurantmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pja.edu.pl.s26772.restaurantmanagement.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query("SELECT p FROM Product p")
    List<Product> getAllProducts();

    @Query("SELECT p FROM Product p WHERE p.name = :productName")
    Optional<Product> getProductByName(@Param("productName") String productName);
}

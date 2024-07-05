package pja.edu.pl.s26772.restaurantmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pja.edu.pl.s26772.restaurantmanagement.models.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    @Query("SELECT c FROM Category c")
    List<Category> getAllCategories();

    @Query("SELECT c FROM Category c WHERE c.name = :categoryName")
    Optional<Category> getCategoryByName(@Param("categoryName") String categoryName);
}

package pja.edu.pl.s26772.restaurantmanagement.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pja.edu.pl.s26772.restaurantmanagement.models.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
    @Query("SELECT r.name, p.name, SUM(r.quantity) FROM Recipe r " +
            "JOIN r.id.product p " +
            "GROUP BY r.name, p.name")
    List<Object[]> findUniqueRecipesWithProducts();

    @Query("SELECT r FROM Recipe r WHERE r.id = :recipeId")
    Optional<Recipe> getRecipeById(@Param("recipeId") Recipe.RecipePK recipeId);

    @Query("SELECT r.name, p.name, r.quantity FROM Recipe r JOIN r.id.product p WHERE r.name = :recipeName")
    List<Object[]> findUniqueRecipeWithProductsByName(@Param("recipeName") String recipeName);

    @Query("DELETE FROM Recipe r WHERE r.name = :recipeName")
    void deleteByName(@Param("recipeName") String recipeName);

    @Query("SELECT r FROM Recipe r WHERE r.name = :recipeName")
    List<Recipe> getByRecipeName(@Param("recipeName")String recipeName);
}

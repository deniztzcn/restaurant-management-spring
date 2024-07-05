package pja.edu.pl.s26772.restaurantmanagement.services;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.MenuItem;
import pja.edu.pl.s26772.restaurantmanagement.models.Product;
import pja.edu.pl.s26772.restaurantmanagement.models.Recipe;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.RecipeRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.RecipeResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.MenuItemRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.ProductRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.RecipeRepository;

import java.util.*;

@Service
public class RecipeService {
    private RecipeRepository recipeRepository;
    private MenuItemRepository menuItemRepository;
    private ProductRepository productRepository;


    public RecipeService(ProductRepository productRepository,MenuItemRepository menuItemRepository, RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
        this.menuItemRepository = menuItemRepository;
        this.productRepository = productRepository;
    }

    public List<RecipeResponseDto> getAllRecipes() {
        List<Object[]> queryResult = recipeRepository.findUniqueRecipesWithProducts();
        Map<String, Map<String, Double>> recipeMap = new HashMap<>();
        List<RecipeResponseDto> result = new ArrayList<>();
        for (Object[] row : queryResult) {
            String recipeName = (String) row[0];
            String productName = (String) row[1];
            Double quantity = (Double) row[2];

            recipeMap.putIfAbsent(recipeName, new HashMap<>());
            Map<String, Double> productMap = recipeMap.get(recipeName);
            productMap.put(productName, quantity);
        }

        for (Map.Entry<String, Map<String, Double>> entry : recipeMap.entrySet()) {
            RecipeResponseDto dto = new RecipeResponseDto();
            dto.setName(entry.getKey());
            dto.setListOfProducts(entry.getValue());
            result.add(dto);
        }

        return result;
    }

    public Optional<RecipeResponseDto> getRecipeByName(String recipeName) {
        List<Object[]> queryResult = recipeRepository.findUniqueRecipeWithProductsByName(recipeName);
        if (queryResult.isEmpty()) {
            return Optional.empty();
        }

        Map<String, Double> productMap = new HashMap<>();
        for (Object[] row : queryResult) {
            String productName = (String) row[1];
            Double quantity = (Double) row[2];
            productMap.put(productName, quantity);
        }

        RecipeResponseDto dto = new RecipeResponseDto();
        dto.setName(recipeName);
        dto.setListOfProducts(productMap);

        return Optional.of(dto);
    }

    @Transactional
    public void addRecipe(RecipeRequestDto requestDto) {
        MenuItem item = menuItemRepository.findById(requestDto.getMenuItemId()).orElseThrow();
        Product product = productRepository.findById(requestDto.getProductId()).orElseThrow();

        Recipe recipe = new Recipe();
        recipe.setName(requestDto.getName());
        recipe.setQuantity(requestDto.getQuantity());
        Recipe.RecipePK id = new Recipe.RecipePK();
        id.setProduct(product);
        id.setMenuItem(item);
        recipe.setId(id);
        recipeRepository.save(recipe);
    }

    @Transactional
    @Modifying
    public void deleteRecipe(String recipeName){
        List<Recipe> recipes = recipeRepository.getByRecipeName(recipeName);
        if(recipes.isEmpty()){
            throw new NoSuchElementException();
        }
        recipeRepository.deleteByName(recipeName);
    }

    @Transactional
    public Optional<RecipeResponseDto> updateRecipe(RecipeRequestDto requestDto) {
        MenuItem menuItem = menuItemRepository.findById(requestDto.getMenuItemId())
                .orElseThrow();
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow();

        Recipe.RecipePK id = new Recipe.RecipePK();
        id.setMenuItem(menuItem);
        id.setProduct(product);

        Recipe recipe = recipeRepository.getRecipeById(id).orElseThrow();
        recipe.setName(requestDto.getName());
        recipe.setQuantity(requestDto.getQuantity());
        recipeRepository.save(recipe);
        return getRecipeByName(recipe.getName());
    }
}

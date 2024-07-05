package pja.edu.pl.s26772.restaurantmanagement.controllers.apiControllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.RecipeRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.RecipeResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.services.RecipeService;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

//VERY PROBLEMATIC API ESPECIALLY UPDATE, TRY TO THINK ABOUT BETTER DTO MAPPER LOGIC
@RestController
@RequestMapping(path="/api/recipes", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RecipeApiController {
    private final RecipeService recipeService;

    public RecipeApiController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Tag(name = "GET", description = "Get information about all recipes.")
    @GetMapping()
    public ResponseEntity<List<RecipeResponseDto>> getAllRecipes(){
        List<RecipeResponseDto> responseDtoList = recipeService.getAllRecipes();
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "GET", description = "Get information about a recipe.")
    @GetMapping("/{recipeName}")
    public ResponseEntity<?> getRecipeByName(@PathVariable String recipeName){
        try{
            RecipeResponseDto responseDto = recipeService.getRecipeByName(recipeName).orElseThrow();
            return ResponseEntity.ok(responseDto);
        } catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }
//
//
    @Tag(name = "POST", description = "Add recipe.")
    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody RecipeRequestDto requestDto) {
        recipeService.addRecipe(requestDto);
        return ResponseEntity.ok().build();
    }
//
    @Tag(name = "DELETE", description = "Delete recipe")
    @DeleteMapping("delete/{recipeName}")
    public ResponseEntity<?> deleteRecipe(@PathVariable String recipeName){
        try{
            recipeService.deleteRecipe(recipeName);
        } catch (NoSuchElementException ex){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }
//
    @Tag(name = "Update", description = "Update recipe information")
    @PutMapping("update")
    public ResponseEntity<?> updateRecipe(@RequestBody RecipeRequestDto requestDto){
        try {
            RecipeResponseDto responseDto = recipeService.updateRecipe(requestDto).orElseThrow();
            return ResponseEntity.ok(responseDto);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recipe not found with menuItemId: " + requestDto.getMenuItemId()
                            + " and productId: " + requestDto.getProductId());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handle(MethodArgumentNotValidException ex){
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}

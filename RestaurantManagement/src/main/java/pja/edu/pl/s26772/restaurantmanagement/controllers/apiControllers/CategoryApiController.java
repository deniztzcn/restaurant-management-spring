package pja.edu.pl.s26772.restaurantmanagement.controllers.apiControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pja.edu.pl.s26772.restaurantmanagement.models.Category;
import pja.edu.pl.s26772.restaurantmanagement.models.Department;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.CategoryRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.CategoryResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.DepartmentResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.services.CategoryService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/categories", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CategoryApiController {
    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;

    public CategoryApiController(CategoryService categoryService, ObjectMapper objectMapper) {
        this.categoryService = categoryService;
        this.objectMapper = objectMapper;
    }

    @Tag(name = "GET", description = "Get information about categories along with the related menu items.")
    @GetMapping()
    public ResponseEntity<List<CategoryResponseDto>> getCategoriesWithMenuItems(){
        List<CategoryResponseDto> responseDtoList = categoryService.getCategoriesWithMenuItems();
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "GET", description = "Get information about a category along with the menu items.")
    @GetMapping("/{categoryName}")
    public ResponseEntity<?> getCategory(@PathVariable String categoryName){
        try{
            Category category = categoryService.getCategoryByName(categoryName).orElseThrow();
            CategoryResponseDto responseDto = categoryService.getResponseDto(category);
            return ResponseEntity.ok(responseDto);
        } catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @Tag(name = "POST", description = "Add new category.")
    @PostMapping
    public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody CategoryRequestDto requestDto) {
        CategoryResponseDto responseDto = categoryService.addCategory(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @Tag(name = "DELETE", description = "Delete category")
    @DeleteMapping("delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
        try{
            Category category = categoryService.getCategory(categoryId).orElseThrow();
            categoryService.deleteCategory(category);
        } catch (NoSuchElementException ex){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "PATCH", description = "Update category information")
    @PatchMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @RequestBody JsonMergePatch patch){

        try {
            Category category = categoryService.getCategory(categoryId).orElseThrow();

            CategoryRequestDto requestDto = categoryService.getRequestDto(category);
            CategoryRequestDto patchedDto = applyPatch(requestDto,patch);
            categoryService.updateCategory(category,patchedDto);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private CategoryRequestDto applyPatch(CategoryRequestDto requestDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode categoryNode = objectMapper.valueToTree(requestDto);
        JsonNode patchNode = patch.apply(categoryNode);
        return objectMapper.treeToValue(patchNode, CategoryRequestDto.class);
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

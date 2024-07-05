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
import pja.edu.pl.s26772.restaurantmanagement.models.MenuItem;
import pja.edu.pl.s26772.restaurantmanagement.models.Product;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.MenuItemRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.ProductRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MenuItemResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.ProductResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.services.MenuItemService;
import pja.edu.pl.s26772.restaurantmanagement.services.ProductService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/products", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ProductApiController {
    private final ProductService productService;
    private final ObjectMapper objectMapper;

    public ProductApiController(ProductService productService, ObjectMapper objectMapper) {
        this.productService = productService;
        this.objectMapper = objectMapper;
    }

    @Tag(name = "GET", description = "Get information about all products.")
    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<ProductResponseDto> responseDtoList = productService.getAllProducts();
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "GET", description = "Get information about a product.")
    @GetMapping("/{productName}")
    public ResponseEntity<?> getProduct(@PathVariable String productName){
        try{
            Product product = productService.getProductByName(productName).orElseThrow();
            ProductResponseDto responseDto = productService.getResponseDto(product);
            return ResponseEntity.ok(responseDto);
        } catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }


    @Tag(name = "POST", description = "Add product.")
    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto requestDto) {
        ProductResponseDto responseDto = productService.addProduct(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @Tag(name = "DELETE", description = "Delete product")
    @DeleteMapping("delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        try{
            Product product = productService.getProduct(productId).orElseThrow();
            productService.deleteProduct(product);
        } catch (NoSuchElementException ex){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "PATCH", description = "Update product information")
    @PatchMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody JsonMergePatch patch){

        try {
            Product product = productService.getProduct(productId).orElseThrow();
            ProductRequestDto requestDto = productService.getRequestDto(product);
            ProductRequestDto patchedDto = applyPatch(requestDto,patch);
            productService.updateProduct(product,patchedDto);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private ProductRequestDto applyPatch(ProductRequestDto requestDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode productNode = objectMapper.valueToTree(requestDto);
        JsonNode patchNode = patch.apply(productNode);
        return objectMapper.treeToValue(patchNode, ProductRequestDto.class);
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

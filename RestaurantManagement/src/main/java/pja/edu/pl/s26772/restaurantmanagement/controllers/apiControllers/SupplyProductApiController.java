package pja.edu.pl.s26772.restaurantmanagement.controllers.apiControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pja.edu.pl.s26772.restaurantmanagement.models.Product;
import pja.edu.pl.s26772.restaurantmanagement.models.SupplyProduct;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.ProductRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.SupplyProductRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.ProductResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.SupplyProductResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.services.SupplyProductService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/deliveries-warehouse")
public class SupplyProductApiController {
    private final SupplyProductService supplyProductService;
    private final ObjectMapper objectMapper;

    public SupplyProductApiController(SupplyProductService supplyProductService, ObjectMapper objectMapper) {
        this.supplyProductService = supplyProductService;
        this.objectMapper = objectMapper;
    }

    @Tag(name = "GET", description = "Get information about product deliveries to warehouse.")
    @GetMapping()
    public ResponseEntity<List<SupplyProductResponseDto>> getAllDeliveries(){
        List<SupplyProductResponseDto> responseDtoList = supplyProductService.getAllDeliveries();
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "GET", description = "Get information about deliveries for given product name.")
    @GetMapping("/{productName}")
    public ResponseEntity<?> getDeliveriesByProductName(@PathVariable String productName){
        try{
            List<SupplyProductResponseDto> responseDtoList = supplyProductService.getDeliveriesProductName(productName);
            return ResponseEntity.ok(responseDtoList);
        } catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @Tag(name = "GET", description = "Get information about the last delivery for given product name.")
    @GetMapping("last-delivery/{productName}")
    public ResponseEntity<?> getLastDeliveryByProductName(@PathVariable String productName){
        try{
            SupplyProductResponseDto responseDto = supplyProductService.getLastDeliveryByProductName(productName);
            return ResponseEntity.ok(responseDto);
        } catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }


    @Tag(name = "POST", description = "Add a delivery.")
    @PostMapping
    public ResponseEntity<SupplyProductResponseDto> addDelivery(@RequestBody SupplyProductRequestDto requestDto) {
        SupplyProductResponseDto responseDto = supplyProductService.addDelivery(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @Tag(name = "DELETE", description = "Delete a delivery")
    @DeleteMapping("delete/{supplyProductId}")
    public ResponseEntity<?> deleteDelivery(@PathVariable Long supplyProductId){
        try{
            SupplyProduct supplyProduct = supplyProductService.getDeliveryById(supplyProductId).orElseThrow();
            supplyProductService.deleteSupplyProduct(supplyProduct);
        } catch (NoSuchElementException ex){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "PATCH", description = "Update delivery information")
    @PatchMapping("/{supplyProductId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long supplyProductId, @RequestBody JsonMergePatch patch){

        try {
            SupplyProduct sp = supplyProductService.getDeliveryById(supplyProductId).orElseThrow();
            SupplyProductRequestDto requestDto = supplyProductService.getRequestDto(sp);
            SupplyProductRequestDto patchedDto = applyPatch(requestDto,patch);
            supplyProductService.updateSupplyProduct(sp,patchedDto);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private SupplyProductRequestDto applyPatch(SupplyProductRequestDto requestDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode spNode = objectMapper.valueToTree(requestDto);
        JsonNode patchNode = patch.apply(spNode);
        return objectMapper.treeToValue(patchNode, SupplyProductRequestDto.class);
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

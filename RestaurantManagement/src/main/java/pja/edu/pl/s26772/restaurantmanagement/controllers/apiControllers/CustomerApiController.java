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
import pja.edu.pl.s26772.restaurantmanagement.models.Customer;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.CustomerRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.CustomerResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.services.CustomerService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/customers", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CustomerApiController {
    private final CustomerService customerService;
    private final ObjectMapper objectMapper;

    public CustomerApiController(CustomerService customerService, ObjectMapper objectMapper) {
        this.customerService = customerService;
        this.objectMapper = objectMapper;
    }

    @Tag(name = "GET", description = "Get information about customer.")
    @GetMapping()
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        List<CustomerResponseDto> responseDtoList = customerService.getAllCustomers();
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "GET", description = "Get information about customer.")
    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable Long customerId) {
        try {
            Customer customer = customerService.getCustomer(customerId).orElseThrow();
            CustomerResponseDto responseDto = customerService.getResponseDto(customer);
            return ResponseEntity.ok(responseDto);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Tag(name = "POST", description = "Add menu item.")
    @PostMapping
    public ResponseEntity<CustomerResponseDto> addCustomer(@RequestBody CustomerRequestDto requestDto) {
        CustomerResponseDto responseDto = customerService.getResponseDto(customerService.addCustomer(requestDto));
        return ResponseEntity.ok(responseDto);
    }

    @Tag(name = "DELETE", description = "Delete customer")
    @DeleteMapping("delete/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        try {
            Customer customer = customerService.getCustomer(customerId).orElseThrow();
            customerService.deleteCustomer(customer);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "PATCH", description = "Update customer information")
    @PatchMapping("/{customerId}")
    public ResponseEntity<?> updateMenuItem(@PathVariable Long customerId, @RequestBody JsonMergePatch patch) {

        try {
            Customer customer = customerService.getCustomer(customerId).orElseThrow();

            CustomerRequestDto requestDto = customerService.getRequestDto(customer);
            CustomerRequestDto patchedDto = applyPatch(requestDto, patch);
            customerService.updateCustomer(customer, patchedDto);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private CustomerRequestDto applyPatch(CustomerRequestDto requestDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode customerNode = objectMapper.valueToTree(requestDto);
        JsonNode patchNode = patch.apply(customerNode);
        return objectMapper.treeToValue(patchNode, CustomerRequestDto.class);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handle(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}

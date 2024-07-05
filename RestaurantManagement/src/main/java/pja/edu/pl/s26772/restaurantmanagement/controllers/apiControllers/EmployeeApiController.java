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
import pja.edu.pl.s26772.restaurantmanagement.models.Employee;
import pja.edu.pl.s26772.restaurantmanagement.models.MenuItem;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.EmployeeRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.MenuItemRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.EmployeeResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MenuItemResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.services.EmployeeService;
import pja.edu.pl.s26772.restaurantmanagement.services.MenuItemService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
//DELETE OPERATION DOES NOT WORK PROPERLY
@RestController
@RequestMapping(path="/api/employees", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class EmployeeApiController {
    private final EmployeeService employeeService;
    private final ObjectMapper objectMapper;

    public EmployeeApiController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    @Tag(name = "GET", description = "Get information about employees.")
    @GetMapping()
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees(){
        List<EmployeeResponseDto> responseDtoList = employeeService.getAllEmployees();
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "GET", description = "Get information about an employee.")
    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployee(@PathVariable Long employeeId){
        try{
            Employee employee = employeeService.getEmployee(employeeId).orElseThrow();
            EmployeeResponseDto responseDto = employeeService.getResponseDto(employee);
            return ResponseEntity.ok(responseDto);
        } catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @Tag(name = "POST", description = "Add employee.")
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> addEmployee(@RequestBody EmployeeRequestDto requestDto) {
        EmployeeResponseDto responseDto = employeeService.addEmployee(requestDto);
        return ResponseEntity.ok(responseDto);
    }
    @Tag(name = "DELETE", description = "Delete employee")
    @DeleteMapping("delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId){
        try{
            Employee employee = employeeService.getEmployee(employeeId).orElseThrow();
            employeeService.deleteEmployee(employee);
        } catch (NoSuchElementException ex){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "PATCH", description = "Update employee information")
    @PatchMapping("/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId, @RequestBody JsonMergePatch patch){

        try {
            Employee employee = employeeService.getEmployee(employeeId).orElseThrow();

            EmployeeRequestDto requestDto = employeeService.getRequestDto(employee);
            EmployeeRequestDto patchedDto = applyPatch(requestDto,patch);
            employeeService.updateEmployee(employee,patchedDto);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private EmployeeRequestDto applyPatch(EmployeeRequestDto requestDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode employeeNode = objectMapper.valueToTree(requestDto);
        JsonNode patchNode = patch.apply(employeeNode);
        return objectMapper.treeToValue(patchNode, EmployeeRequestDto.class);
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


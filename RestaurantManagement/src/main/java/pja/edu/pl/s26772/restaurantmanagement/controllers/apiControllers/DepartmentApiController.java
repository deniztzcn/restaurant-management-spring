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
import pja.edu.pl.s26772.restaurantmanagement.models.Department;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.DepartmentRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.DepartmentResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.services.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/departments", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class DepartmentApiController {
    private final DepartmentService departmentService;
    private final ObjectMapper objectMapper;

    public DepartmentApiController(DepartmentService departmentService, ObjectMapper objectMapper) {
        this.departmentService = departmentService;
        this.objectMapper = objectMapper;
    }

    @Tag(name = "GET", description = "Get information about departments along with the employees.")
    @GetMapping()
    public ResponseEntity<List<DepartmentResponseDto>> getDepartmentWithEmployees(){
        List<DepartmentResponseDto> responseDtoList = departmentService.getDepartmentWithEmployees();
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "GET", description = "Get information about a department along with the employees.")
    @GetMapping("/{deptName}")
    public ResponseEntity<?> getDepartment(@PathVariable String deptName){
        try{
            Department department = departmentService.getDepartmentByName(deptName).orElseThrow();
            DepartmentResponseDto responseDto = departmentService.getResponseDto(department);
            return ResponseEntity.ok(responseDto);
        } catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @Tag(name = "POST", description = "Add new department.")
    @PostMapping
    public ResponseEntity<DepartmentResponseDto> addDepartment(@RequestBody DepartmentRequestDto requestDto) {
        DepartmentResponseDto responseDto = departmentService.addDepartment(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @Tag(name = "DELETE", description = "Delete department")
    @DeleteMapping("delete/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long departmentId){
        try{
            Department department = departmentService.getDepartment(departmentId).orElseThrow();
            departmentService.deleteDepartment(department);
        } catch (NoSuchElementException ex){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "PATCH", description = "Update department information")
    @PatchMapping("/{departmentId}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long departmentId, @RequestBody JsonMergePatch patch){

        try {
            Department department = departmentService.getDepartment(departmentId).orElseThrow();

            DepartmentRequestDto requestDto = departmentService.getRequestDto(department);
            DepartmentRequestDto patchedDto = applyPatch(requestDto,patch);
            departmentService.updateDepartment(department,patchedDto);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private DepartmentRequestDto applyPatch(DepartmentRequestDto requestDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode departmentNode = objectMapper.valueToTree(requestDto);
        JsonNode patchNode = patch.apply(departmentNode);
        return objectMapper.treeToValue(patchNode, DepartmentRequestDto.class);
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

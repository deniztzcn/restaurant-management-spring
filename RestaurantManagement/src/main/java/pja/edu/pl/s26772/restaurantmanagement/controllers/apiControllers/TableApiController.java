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
import pja.edu.pl.s26772.restaurantmanagement.models.Tables;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.ProductRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.ProductResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.TableResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.services.ProductService;
import pja.edu.pl.s26772.restaurantmanagement.services.TableService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/tables")
public class TableApiController {
    private final TableService tableService;

    public TableApiController(TableService tableService) {
        this.tableService = tableService;
    }

    @Tag(name = "GET", description = "Get information about currently occupied tables.")
    @GetMapping("occupied")
    public ResponseEntity<List<TableResponseDto>> getTables(){
        List<TableResponseDto> responseDtoList = tableService.getTables();
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "GET", description = "Get information about a specific table.")
    @GetMapping("/{tableId}")
    public ResponseEntity<?> getTable(@PathVariable Long tableId){
        try{
            TableResponseDto responseDto = tableService.getTableById(tableId);
            return ResponseEntity.ok(responseDto);
        } catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }


    @Tag(name = "POST", description = "Add table.")
    @PostMapping
    public ResponseEntity<Void> addTable() {
        tableService.addTable();
        return ResponseEntity.ok().build();
    }

    @Tag(name = "DELETE", description = "Delete table")
    @DeleteMapping("delete/{tableId}")
    public ResponseEntity<?> deleteTable(@PathVariable Long tableId){
        try{
            Tables tables = tableService.getTable(tableId).orElseThrow();
            tableService.deleteTable(tables);
        } catch (NoSuchElementException ex){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

}

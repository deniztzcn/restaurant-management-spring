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
import pja.edu.pl.s26772.restaurantmanagement.models.Visit;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.MenuItemRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.VisitRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MenuItemResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.VisitResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.services.MenuItemService;
import pja.edu.pl.s26772.restaurantmanagement.services.VisitService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/visits", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class VisitApiController {
    private final VisitService visitService;
    private final ObjectMapper objectMapper;

    public VisitApiController(VisitService visitService, ObjectMapper objectMapper) {
        this.visitService =visitService ;
        this.objectMapper = objectMapper;
    }

    @Tag(name = "GET", description = "Get information about all visits.")
    @GetMapping()
    public ResponseEntity<List<VisitResponseDto>> getVisits(){
        List<VisitResponseDto> responseDtoList = visitService.getVists();
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "GET", description = "Get information about a visit.")
    @GetMapping("/{visitId}")
    public ResponseEntity<?> getVisitByVisitId(@PathVariable Long visitId){
        try{
            Visit visit = visitService.getVisitById(visitId).orElseThrow();
            VisitResponseDto responseDto = visitService.getResponseDto(visit);
            return ResponseEntity.ok(responseDto);
        } catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @Tag(name = "GET", description = "Get information about ongoing visits.")
    @GetMapping("current")
    public ResponseEntity<?> getCurrentVisits(){
        try{
            List<VisitResponseDto> visits = visitService.getCurrentVists();
            return ResponseEntity.ok(visits);
        } catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @Tag(name = "GET", description = "Get information about a visit for a specific customer.")
    @GetMapping("/{customerId}")
    public ResponseEntity<?> getVisitByCustomerId(@PathVariable Long customerId){
        try{
            List<VisitResponseDto> visits = visitService.getVisitsByCustomerId(customerId);
            return ResponseEntity.ok(visits);
        } catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }


    @Tag(name = "POST", description = "Add visit.")
    @PostMapping
    public ResponseEntity<VisitResponseDto> addVisit(@RequestBody VisitRequestDto requestDto) {
        VisitResponseDto responseDto = visitService.addVisit(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @Tag(name = "DELETE", description = "Delete visit")
    @DeleteMapping("delete/{visitId}")
    public ResponseEntity<?> deleteVisit(@PathVariable Long visitId){
        try{
            Visit visit = visitService.getVisitById(visitId).orElseThrow();
            visitService.deleteVisit(visit);
        } catch (NoSuchElementException ex){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "PATCH", description = "Update visit information")
    @PatchMapping("/{visitId}")
    public ResponseEntity<?> updateVisit(@PathVariable Long visitId, @RequestBody JsonMergePatch patch){

        try {
            Visit visit = visitService.getVisitById(visitId).orElseThrow();

            VisitRequestDto requestDto = visitService.getRequestDto(visit);
            VisitRequestDto patchedDto = applyPatch(requestDto,patch);
            visitService.updateVisit(visit,patchedDto);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private VisitRequestDto applyPatch(VisitRequestDto requestDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode visitNode = objectMapper.valueToTree(requestDto);
        JsonNode patchNode = patch.apply(visitNode);
        return objectMapper.treeToValue(patchNode, VisitRequestDto.class);
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

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
import pja.edu.pl.s26772.restaurantmanagement.models.Reservation;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.EmployeeRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.ReservationRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.EmployeeResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.ReservationResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.services.EmployeeService;
import pja.edu.pl.s26772.restaurantmanagement.services.ReservationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/reservations", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ReservationApiController {
    private final ReservationService reservationService;
    private final ObjectMapper objectMapper;

    public ReservationApiController(ReservationService reservationService, ObjectMapper objectMapper) {
        this.reservationService = reservationService;
        this.objectMapper = objectMapper;
    }

    @Tag(name = "GET", description = "Get information about reservations for current day.")
    @GetMapping("current-day")
    public ResponseEntity<List<ReservationResponseDto>> getCurrentReservations(){
        List<ReservationResponseDto> responseDtoList = reservationService.getCurrentReservations();
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "GET", description = "Get information about reservations for given day.")
    @GetMapping("{date}")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByDate(@PathVariable LocalDate date){
        List<ReservationResponseDto> responseDtoList = reservationService.getReservationsByDate(date);
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "GET", description = "Get information about reservations that are not done for a given customer id.")
    @GetMapping("{customerId}")
    public ResponseEntity<List<ReservationResponseDto>> getCurrentReservations(@PathVariable Long customerId){
        List<ReservationResponseDto> responseDtoList = reservationService.getReservationsByCustomerId(customerId);
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "POST", description = "Add reservation.")
    @PostMapping
    public ResponseEntity<ReservationResponseDto> addReservation(@RequestBody ReservationRequestDto requestDto) {
        ReservationResponseDto responseDto = reservationService.addReservation(requestDto);
        return ResponseEntity.ok(responseDto);
    }
    @Tag(name = "DELETE", description = "Delete reservation")
    @DeleteMapping("delete/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId){
        try{
            Reservation reservation = reservationService.getReservation(reservationId).orElseThrow();
            reservationService.deleteReservation(reservation);
        } catch (NoSuchElementException ex){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "PATCH", description = "Update reservation information")
    @PatchMapping("/{reservationId}")
    public ResponseEntity<?> updateReservation(@PathVariable Long reservationId, @RequestBody JsonMergePatch patch){

        try {
            Reservation reservation = reservationService.getReservation(reservationId).orElseThrow();

            ReservationRequestDto requestDto = reservationService.getRequestDto(reservation);
            ReservationRequestDto patchedDto = applyPatch(requestDto,patch);
            reservationService.updateReservation(reservation,patchedDto);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private ReservationRequestDto applyPatch(ReservationRequestDto requestDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode reservationNode = objectMapper.valueToTree(requestDto);
        JsonNode patchNode = patch.apply(reservationNode);
        return objectMapper.treeToValue(patchNode, ReservationRequestDto.class);
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

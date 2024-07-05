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
import pja.edu.pl.s26772.restaurantmanagement.models.Orders;
import pja.edu.pl.s26772.restaurantmanagement.models.Visit;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.OrderRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.VisitRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MenuItemResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MostOrderedItemJson;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.OrderResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.VisitResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.services.MenuItemService;
import pja.edu.pl.s26772.restaurantmanagement.services.OrderService;
import pja.edu.pl.s26772.restaurantmanagement.services.VisitService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/orders", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class OrderApiController {
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    public OrderApiController(OrderService orderService, ObjectMapper objectMapper) {
        this.orderService = orderService ;
        this.objectMapper = objectMapper;
    }

    @Tag(name = "GET", description = "Get information about undelivered orders.")
    @GetMapping("undelivered")
    public ResponseEntity<List<OrderResponseDto>> getUndeliveredOrders(){
        List<OrderResponseDto> responseDtoList = orderService.getUndeliveredOrders();
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "GET", description = "Get information about undelivered orders for given table.")
    @GetMapping("undelivered/{tableId}")
    public ResponseEntity<List<OrderResponseDto>> getUndeliveredForGivenTable(@PathVariable Long tableId){
        List<OrderResponseDto> responseDtoList = orderService.getUndeliveredOrderForGivenTable(tableId);
        return ResponseEntity.ok(responseDtoList);
    }

    @Tag(name = "POST", description = "Add order.")
    @PostMapping
    public ResponseEntity<OrderResponseDto> addOrder(@RequestBody OrderRequestDto requestDto) {
        OrderResponseDto responseDto = orderService.addOrder(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @Tag(name = "DELETE", description = "Delete order")
    @DeleteMapping("delete/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId){
        try{
            Orders order = orderService.getOrderById(orderId).orElseThrow();
            orderService.deleteOrder(order);
        } catch (NoSuchElementException ex){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "PATCH", description = "Update order information")
    @PatchMapping("/{visitId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody JsonMergePatch patch){

        try {
            Orders order = orderService.getOrderById(orderId).orElseThrow();

            OrderRequestDto requestDto = orderService.getRequestDto(order);
            OrderRequestDto patchedDto = applyPatch(requestDto,patch);
            orderService.updateOrder(order,patchedDto);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private OrderRequestDto applyPatch(OrderRequestDto requestDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode orderNode = objectMapper.valueToTree(requestDto);
        JsonNode patchNode = patch.apply(orderNode);
        return objectMapper.treeToValue(patchNode, OrderRequestDto.class);
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

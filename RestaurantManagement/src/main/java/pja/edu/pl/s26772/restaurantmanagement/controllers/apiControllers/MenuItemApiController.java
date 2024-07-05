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
import pja.edu.pl.s26772.restaurantmanagement.constraints.passwordConstraints.HasLowercase;
import pja.edu.pl.s26772.restaurantmanagement.models.Department;
import pja.edu.pl.s26772.restaurantmanagement.models.MenuItem;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.DepartmentRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.MenuItemRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.DepartmentResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MenuItemResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MostOrderedItemJson;
import pja.edu.pl.s26772.restaurantmanagement.services.DepartmentService;
import pja.edu.pl.s26772.restaurantmanagement.services.MenuItemService;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/menu-items", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class MenuItemApiController {
    private final MenuItemService menuItemService;
    private final ObjectMapper objectMapper;

    public MenuItemApiController(MenuItemService menuItemService, ObjectMapper objectMapper) {
        this.menuItemService = menuItemService;
        this.objectMapper = objectMapper;
    }

    @Tag(name = "GET", description = "Get information about menu items.")
    @GetMapping()
    public ResponseEntity<List<MenuItemResponseDto>> getMenuItems(){
        List<MenuItemResponseDto> responseDtoList = menuItemService.getMenuItems();
        return ResponseEntity.ok(responseDtoList);
    }
    @Tag(name = "GET", description = "Get information about the most ordered menu item for specific date.")
    @GetMapping("most-common/{date}")
    public ResponseEntity<?> getMostOrderedItem(@PathVariable LocalDate date){
        Object[] pair = menuItemService.getMostOrderedMenuItem(date);
        MenuItem item = (MenuItem) pair[0];
        MenuItemResponseDto responseDto = menuItemService.getResponseDto(item);
        Long quantity = (Long) pair[1];
        MostOrderedItemJson response = new MostOrderedItemJson(responseDto,quantity);
        return ResponseEntity.ok(response);
    }

    @Tag(name = "GET", description = "Get information about a menu item.")
    @GetMapping("/{itemName}")
    public ResponseEntity<?> getMenuItem(@PathVariable String itemName){
        try{
            MenuItem menuItem = menuItemService.getMenuItemByName(itemName).orElseThrow();
            MenuItemResponseDto responseDto = menuItemService.getResponseDto(menuItem);
            return ResponseEntity.ok(responseDto);
        } catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }


    @Tag(name = "POST", description = "Add menu item.")
    @PostMapping
    public ResponseEntity<MenuItemResponseDto> addMenuItem(@RequestBody MenuItemRequestDto requestDto) {
        MenuItemResponseDto responseDto = menuItemService.addMenuItem(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @Tag(name = "DELETE", description = "Delete menu item")
    @DeleteMapping("delete/{menuItemId}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable Long menuItemId){
        try{
            MenuItem item = menuItemService.getMenuItem(menuItemId).orElseThrow();
            menuItemService.deleteMenuItem(item);
        } catch (NoSuchElementException ex){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "PATCH", description = "Update menu item information")
    @PatchMapping("/{menuItemId}")
    public ResponseEntity<?> updateMenuItem(@PathVariable Long menuItemId, @RequestBody JsonMergePatch patch){

        try {
            MenuItem item = menuItemService.getMenuItem(menuItemId).orElseThrow();

            MenuItemRequestDto requestDto = menuItemService.getRequestDto(item);
            MenuItemRequestDto patchedDto = applyPatch(requestDto,patch);
            menuItemService.updateMenuItem(item,patchedDto);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private MenuItemRequestDto applyPatch(MenuItemRequestDto requestDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode menuItemNode = objectMapper.valueToTree(requestDto);
        JsonNode patchNode = patch.apply(menuItemNode);
        return objectMapper.treeToValue(patchNode, MenuItemRequestDto.class);
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

package pja.edu.pl.s26772.restaurantmanagement.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Category;
import pja.edu.pl.s26772.restaurantmanagement.models.MenuItem;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.MenuItemRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MenuItemResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.CategoryRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.MenuItemRepository;
import pja.edu.pl.s26772.restaurantmanagement.services.mappers.MenuItemMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuItemService(CategoryRepository categoryRepository, MenuItemRepository menuItemRepository, MenuItemMapper menuItemMapper) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;
        this.categoryRepository = categoryRepository;
    }

    public List<MenuItemResponseDto> getMenuItems(){

        List<MenuItem> menuItems = menuItemRepository.getAllMenuItems();
        List<MenuItemResponseDto> dtos = new ArrayList<>();
        for(MenuItem item : menuItems){
            MenuItemResponseDto dto = menuItemMapper.menuItemToResponseDto(item);
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public MenuItemResponseDto addMenuItem(MenuItemRequestDto requestDto) {
        MenuItem item = menuItemMapper.requestToMenuItem(requestDto);
        try{
            Category category = categoryRepository.getCategoryByName(requestDto.getCategoryName()).orElseThrow();
            item.setCategory(category);
        }catch(NoSuchElementException ex){
            Category category = new Category();
            category.setName(requestDto.getCategoryName());
            item.setCategory(category);
        }
        return menuItemMapper.menuItemToResponseDto(menuItemRepository.save(item));
    }

    public Optional<MenuItem> getMenuItem(Long menuItemId) {
        return menuItemRepository.findById(menuItemId);
    }

    public MenuItemRequestDto getRequestDto(MenuItem item){
        return menuItemMapper.menuItemToRequestDto(item);
    }
    @Transactional
    public void deleteMenuItem(MenuItem item) {
        menuItemRepository.delete(item);
    }

    @Transactional
    public void updateMenuItem(MenuItem item, MenuItemRequestDto patchedDto) {
        try{
            Category category = categoryRepository.getCategoryByName(patchedDto.getCategoryName()).orElseThrow();
            item.setCategory(category);
        }catch(NoSuchElementException ex){
            Category category = new Category();
            category.setName(patchedDto.getCategoryName());
            item.setCategory(category);
        }
        item.setPrice(patchedDto.getPrice());
        item.setDescription(patchedDto.getDescription());
        item.setName(patchedDto.getName());
        menuItemRepository.save(item);
    }

    public Optional<MenuItem> getMenuItemByName(String itemName) {
        return menuItemRepository.getMenuItemByName(itemName);
    }

    public MenuItemResponseDto getResponseDto(MenuItem menuItem){
        return menuItemMapper.menuItemToResponseDto(menuItem);
    }

    public Object[] getMostOrderedMenuItem(LocalDate date){
        List<Object[]> menuItemsWithQuantity = menuItemRepository.findMostOrderedMenuItemAtDate(date);
        return menuItemsWithQuantity.getFirst();
    }
}

package pja.edu.pl.s26772.restaurantmanagement.services.mappers;

import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.MenuItem;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.MenuItemRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MenuItemResponseDto;

@Service
public class MenuItemMapper {
    public MenuItemResponseDto menuItemToResponseDto(MenuItem menuItem){
        MenuItemResponseDto dto = new MenuItemResponseDto();
        dto.setName(menuItem.getName());
        dto.setDescription(menuItem.getDescription());
        dto.setPrice(menuItem.getPrice());
        dto.setCategoryName(menuItem.getCategory().getName());
        return dto;
    }

    public MenuItem requestToMenuItem(MenuItemRequestDto requestDto){
        MenuItem menuItem = new MenuItem();
        menuItem.setName(requestDto.getName());
        menuItem.setDescription(requestDto.getDescription());
        menuItem.setPrice(requestDto.getPrice());
        return menuItem;
    }

    public MenuItemRequestDto menuItemToRequestDto(MenuItem item){
        MenuItemRequestDto menuItemRequestDto = new MenuItemRequestDto();
        menuItemRequestDto.setName(item.getName());
        menuItemRequestDto.setDescription(item.getDescription());
        menuItemRequestDto.setPrice(item.getPrice());
        menuItemRequestDto.setCategoryName(item.getCategory().getName());
        return menuItemRequestDto;
    }
}

package pja.edu.pl.s26772.restaurantmanagement.services.mappers;

import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Category;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.CategoryRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.CategoryResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MenuItemResponseDto;

import java.util.List;

@Service
public class CategoryMapper {
    private final MenuItemMapper menuItemMapper;

    public CategoryMapper(MenuItemMapper menuItemMapper) {
        this.menuItemMapper = menuItemMapper;
    }

    public CategoryResponseDto categoryToResponseDto(Category category){
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setCategoryId(category.getId());
        responseDto.setName(category.getName());
        List<MenuItemResponseDto> items = category.getMenuItems().stream().map(menuItemMapper::menuItemToResponseDto).toList();
        responseDto.setMenuItems(items);
        return responseDto;
    }

    public Category requestDtoToCategory(CategoryRequestDto requestDto){
        Category category = new Category ();
        category.setName(requestDto.getName());
        return category;
    }

    public CategoryRequestDto categoryToRequestDto(Category category){
        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setName(category.getName());
        return requestDto;
    }
}

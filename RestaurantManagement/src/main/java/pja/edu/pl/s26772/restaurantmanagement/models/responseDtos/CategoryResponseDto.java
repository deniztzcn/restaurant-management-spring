package pja.edu.pl.s26772.restaurantmanagement.models.responseDtos;

import pja.edu.pl.s26772.restaurantmanagement.models.MenuItem;

import java.util.List;

public class CategoryResponseDto {
    private Long categoryId;
    private String name;
    private List<MenuItemResponseDto> menuItems;

    public CategoryResponseDto() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItemResponseDto> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemResponseDto> menuItems) {
        this.menuItems = menuItems;
    }
}

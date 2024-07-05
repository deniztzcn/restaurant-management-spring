package pja.edu.pl.s26772.restaurantmanagement.models.requestDtos;

import java.util.List;
import java.util.Map;

public class RecipeRequestDto {
    private String name;
    private Long menuItemId;
    private Long productId;
    private double quantity;

    public RecipeRequestDto() {
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}


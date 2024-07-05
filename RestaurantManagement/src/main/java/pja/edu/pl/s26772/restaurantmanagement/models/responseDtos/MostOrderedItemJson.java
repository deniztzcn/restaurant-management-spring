package pja.edu.pl.s26772.restaurantmanagement.models.responseDtos;

public class MostOrderedItemJson {
    private MenuItemResponseDto menuItem;
    private Long quantity;

    public MostOrderedItemJson(MenuItemResponseDto menuItem, Long quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItemResponseDto getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItemResponseDto menuItem) {
        this.menuItem = menuItem;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}

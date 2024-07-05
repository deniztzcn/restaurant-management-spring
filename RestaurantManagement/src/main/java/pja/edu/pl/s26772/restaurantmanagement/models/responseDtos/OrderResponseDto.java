package pja.edu.pl.s26772.restaurantmanagement.models.responseDtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderResponseDto {
    private int quantity;
    private LocalDateTime orderedTime;

    private LocalDateTime deliveredTime;
    private MenuItemResponseDto menuItem;

    private Long tableId;

    public OrderResponseDto() {
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalDateTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public LocalDateTime getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(LocalDateTime deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MenuItemResponseDto getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItemResponseDto menuItem) {
        this.menuItem = menuItem;
    }
}

package pja.edu.pl.s26772.restaurantmanagement.models.requestDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints.EmployeeExists;
import pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints.MenuItemExists;
import pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints.VisitExists;

public class OrderRequestDto {
    @Positive
    @NotNull
    @VisitExists
    private Long visitId;

    @Positive
    @NotNull
    @MenuItemExists
    private Long menuItemId;

    @Positive
    @EmployeeExists
    private Long employeeId;

    @Positive
    @NotNull
    private int quantity;

    public OrderRequestDto() {
    }

    public Long getVisitId() {
        return visitId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

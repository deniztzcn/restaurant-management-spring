package pja.edu.pl.s26772.restaurantmanagement.models.responseDtos;

import java.util.List;

public class TableResponseDto {
    private Long tableId;
    private CustomerResponseDto customer;
    private List<OrderResponseDto> orders;

    public TableResponseDto() {
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public CustomerResponseDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponseDto customer) {
        this.customer = customer;
    }

    public List<OrderResponseDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponseDto> orders) {
        this.orders = orders;
    }
}

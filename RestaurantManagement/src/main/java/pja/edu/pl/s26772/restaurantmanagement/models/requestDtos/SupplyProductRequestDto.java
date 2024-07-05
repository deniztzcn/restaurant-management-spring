package pja.edu.pl.s26772.restaurantmanagement.models.requestDtos;

import java.time.LocalDate;

public class SupplyProductRequestDto {
    private String productName;
    private LocalDate date;
    private double quantity;

    public SupplyProductRequestDto() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}

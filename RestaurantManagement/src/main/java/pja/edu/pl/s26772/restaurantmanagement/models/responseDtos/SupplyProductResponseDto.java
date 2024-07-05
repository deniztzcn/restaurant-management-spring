package pja.edu.pl.s26772.restaurantmanagement.models.responseDtos;

import java.time.LocalDate;

public class SupplyProductResponseDto {
    private LocalDate date;
    private double quantity;
    private String productName;

    public SupplyProductResponseDto() {
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

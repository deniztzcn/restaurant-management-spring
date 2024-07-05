package pja.edu.pl.s26772.restaurantmanagement.models.requestDtos;

import jakarta.validation.constraints.NotEmpty;
import pja.edu.pl.s26772.restaurantmanagement.constraints.uniqueConstraints.UniqueProduct;

import java.util.List;

public class ProductRequestDto {
    @UniqueProduct
    @NotEmpty
    private String name;
    @NotEmpty
    private String measurement;

    public ProductRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}

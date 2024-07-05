package pja.edu.pl.s26772.restaurantmanagement.models.requestDtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import pja.edu.pl.s26772.restaurantmanagement.constraints.uniqueConstraints.UniqueMenuItem;

public class MenuItemRequestDto {
    @UniqueMenuItem
    private String name;
    private String description;
    @Digits(integer = 23, fraction = 2)
    @Positive
    private double price;
    private String categoryName;

    public MenuItemRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

package pja.edu.pl.s26772.restaurantmanagement.models.requestDtos;

import jakarta.validation.constraints.NotEmpty;
import pja.edu.pl.s26772.restaurantmanagement.constraints.uniqueConstraints.UniqueCategory;

public class CategoryRequestDto {
    @NotEmpty
    @UniqueCategory
    private String name;
    public CategoryRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

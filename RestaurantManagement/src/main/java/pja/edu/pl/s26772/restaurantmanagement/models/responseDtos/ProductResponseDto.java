package pja.edu.pl.s26772.restaurantmanagement.models.responseDtos;

import java.util.List;

public class ProductResponseDto {
    private String name;
    private String measurement;
    private List<String> usedInRecipes;

    public ProductResponseDto() {
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

    public List<String> getUsedInRecipes() {
        return usedInRecipes;
    }

    public void setUsedInRecipes(List<String> usedInRecipes) {
        this.usedInRecipes = usedInRecipes;
    }
}

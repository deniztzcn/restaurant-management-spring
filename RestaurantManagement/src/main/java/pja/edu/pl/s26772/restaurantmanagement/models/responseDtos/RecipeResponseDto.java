package pja.edu.pl.s26772.restaurantmanagement.models.responseDtos;

import java.util.List;
import java.util.Map;

public class RecipeResponseDto {
    private String name;

    private Map<String,Double> listOfProducts;

    public RecipeResponseDto() {
    }

    public Map<String, Double> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(Map<String, Double> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

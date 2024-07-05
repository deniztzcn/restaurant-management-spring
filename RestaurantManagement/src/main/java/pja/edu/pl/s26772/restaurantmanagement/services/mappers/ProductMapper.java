package pja.edu.pl.s26772.restaurantmanagement.services.mappers;

import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Product;
import pja.edu.pl.s26772.restaurantmanagement.models.Recipe;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.ProductRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.ProductResponseDto;

import java.util.List;

@Service
public class ProductMapper {
    public ProductResponseDto productToResponseDto(Product product){
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setName(product.getName());
        responseDto.setMeasurement(product.getMeasurement());
        List<String> listOfRecipes = product.getRecipes().stream().map(Recipe::getName).toList();
        responseDto.setUsedInRecipes(listOfRecipes);
        return responseDto;
    }

    public Product requestDtoToProduct(ProductRequestDto requestDto){
        Product product = new Product();
        product.setName(requestDto.getName());
        product.setMeasurement(requestDto.getMeasurement());
        return product;
    }

    public ProductRequestDto productToRequestDto(Product product) {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName(product.getName());
        requestDto.setMeasurement(product.getMeasurement());
        return requestDto;
    }
}

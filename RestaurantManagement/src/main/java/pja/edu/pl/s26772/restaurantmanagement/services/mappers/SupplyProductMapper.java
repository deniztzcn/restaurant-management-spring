package pja.edu.pl.s26772.restaurantmanagement.services.mappers;

import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.SupplyProduct;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.SupplyProductRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.SupplyProductResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.ProductRepository;

@Service
public class SupplyProductMapper {
    private final ProductRepository productRepository;

    public SupplyProductMapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public SupplyProductResponseDto supplyProductToResponseDto(SupplyProduct delivery){
        SupplyProductResponseDto supplyProductResponseDto = new SupplyProductResponseDto();
        supplyProductResponseDto.setProductName(delivery.getProduct().getName());
        supplyProductResponseDto.setDate(delivery.getDeliveryDate());
        supplyProductResponseDto.setQuantity(delivery.getQuantity());
        return supplyProductResponseDto;
    }

    public SupplyProductRequestDto supplyProductToRequestDto(SupplyProduct supplyProduct){
        SupplyProductRequestDto requestDto = new SupplyProductRequestDto();
        requestDto.setProductName(supplyProduct.getProduct().getName());
        requestDto.setDate(supplyProduct.getDeliveryDate());
        requestDto.setQuantity(supplyProduct.getQuantity());
        return requestDto;
    }

    public SupplyProduct requestDtoToSupplyProduct(SupplyProductRequestDto requestDto){
        SupplyProduct sp = new SupplyProduct();
        sp.setProduct(productRepository.getProductByName(requestDto.getProductName()).get());
        sp.setDeliveryDate(requestDto.getDate());
        sp.setQuantity(requestDto.getQuantity());
        return sp;
    }


}

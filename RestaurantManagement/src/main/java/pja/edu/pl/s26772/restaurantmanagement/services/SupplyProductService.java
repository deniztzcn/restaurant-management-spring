package pja.edu.pl.s26772.restaurantmanagement.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.SupplyProduct;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.SupplyProductRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.SupplyProductResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.SupplyProductRepository;
import pja.edu.pl.s26772.restaurantmanagement.services.mappers.SupplyProductMapper;

import java.util.List;
import java.util.Optional;

@Service
public class SupplyProductService {
    private final ProductService productService;
    private final SupplyProductRepository supplyProductRepository;
    private final SupplyProductMapper supplyProductMapper;

    public SupplyProductService(ProductService productService, SupplyProductRepository supplyProductRepository, SupplyProductMapper supplyProductMapper) {
        this.productService = productService;
        this.supplyProductRepository = supplyProductRepository;
        this.supplyProductMapper = supplyProductMapper;
    }

    public List<SupplyProductResponseDto> getAllDeliveries(){
        List<SupplyProduct> deliveries = supplyProductRepository.getAllDeliveries();
        return deliveries.stream().map(supplyProductMapper::supplyProductToResponseDto).toList();
    }

    public List<SupplyProductResponseDto> getDeliveriesProductName(String productName){
        List<SupplyProduct> deliveries = supplyProductRepository.getDeliveriesByProductName(productName);
        return deliveries.stream().map(supplyProductMapper::supplyProductToResponseDto).toList();
    }

    public SupplyProductResponseDto getLastDeliveryByProductName(String productName){
        SupplyProduct delivery = supplyProductRepository.getLastDeliveryByProductName(productName).orElseThrow();
        return supplyProductMapper.supplyProductToResponseDto(delivery);
    }

    public Optional<SupplyProduct> getDeliveryById(Long supplyProductId){
        return supplyProductRepository.findById(supplyProductId);
    }

    public SupplyProductResponseDto getResponseDto(SupplyProduct supplyProduct){
        return supplyProductMapper.supplyProductToResponseDto(supplyProduct);
    }

    @Transactional
    public SupplyProductResponseDto addDelivery(SupplyProductRequestDto requestDto){
        return supplyProductMapper.supplyProductToResponseDto(supplyProductRepository.save(supplyProductMapper.requestDtoToSupplyProduct(requestDto)));
    }

    @Transactional
    public void deleteSupplyProduct(SupplyProduct supplyProduct){
        supplyProductRepository.delete(supplyProduct);
    }

    public SupplyProductRequestDto getRequestDto(SupplyProduct supplyProduct){
        return supplyProductMapper.supplyProductToRequestDto(supplyProduct);
    }

    @Transactional
    public void updateSupplyProduct(SupplyProduct supplyProduct, SupplyProductRequestDto patchedDto){
        supplyProduct.setProduct(productService.getProductByName(patchedDto.getProductName()).get());
        supplyProduct.setQuantity(patchedDto.getQuantity());
        supplyProduct.setDeliveryDate(patchedDto.getDate());
        supplyProductRepository.save(supplyProduct);
    }
}

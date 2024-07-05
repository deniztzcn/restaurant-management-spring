package pja.edu.pl.s26772.restaurantmanagement.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Product;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.ProductRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.ProductResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.ProductRepository;
import pja.edu.pl.s26772.restaurantmanagement.services.mappers.ProductMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.getAllProducts().stream().map(productMapper::productToResponseDto).toList();
    }

    public Optional<Product> getProductByName(String productName){
        return productRepository.getProductByName(productName);
    }

    public ProductResponseDto getResponseDto(Product product) {
        return productMapper.productToResponseDto(product);
    }
    @Transactional
    public ProductResponseDto addProduct(ProductRequestDto requestDto) {
        return productMapper.productToResponseDto(productRepository.save(productMapper.requestDtoToProduct(requestDto)));
    }

    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }
    @Transactional
    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

    public ProductRequestDto getRequestDto(Product product) {
        return productMapper.productToRequestDto(product);
    }

    @Transactional
    public void updateProduct(Product product, ProductRequestDto patchedDto) {
        product.setMeasurement(patchedDto.getMeasurement());
        product.setName(patchedDto.getName());
        productRepository.save(product);
    }
}

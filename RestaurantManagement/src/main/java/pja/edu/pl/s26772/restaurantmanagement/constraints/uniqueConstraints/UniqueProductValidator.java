package pja.edu.pl.s26772.restaurantmanagement.constraints.uniqueConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import pja.edu.pl.s26772.restaurantmanagement.services.ProductService;

@Component
public class UniqueProductValidator implements ConstraintValidator<UniqueProduct,Long> {
    private ProductService productService;

    public UniqueProductValidator() {
    }

    public UniqueProductValidator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void initialize(UniqueProduct constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return productService.getProduct(aLong).isEmpty();
    }
}

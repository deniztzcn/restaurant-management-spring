package pja.edu.pl.s26772.restaurantmanagement.constraints.uniqueConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pja.edu.pl.s26772.restaurantmanagement.services.CategoryService;

@Component
public class UniqueCategoryValidator implements ConstraintValidator<UniqueCategory,String> {
    private CategoryService categoryService;

    public UniqueCategoryValidator() {
    }

    @Autowired
    public UniqueCategoryValidator(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void initialize(UniqueCategory constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !categoryService.getCategoryByName(s).isPresent();
    }
}

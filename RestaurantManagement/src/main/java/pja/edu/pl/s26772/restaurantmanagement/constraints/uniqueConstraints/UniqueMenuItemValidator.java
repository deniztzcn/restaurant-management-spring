package pja.edu.pl.s26772.restaurantmanagement.constraints.uniqueConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pja.edu.pl.s26772.restaurantmanagement.services.MenuItemService;

@Component
public class UniqueMenuItemValidator implements ConstraintValidator<UniqueMenuItem,String> {
    private MenuItemService menuItemService;

    public UniqueMenuItemValidator() {
    }

    @Autowired
    public UniqueMenuItemValidator(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !menuItemService.getMenuItemByName(s).isPresent();
    }

    @Override
    public void initialize(UniqueMenuItem constraintAnnotation) {}
}

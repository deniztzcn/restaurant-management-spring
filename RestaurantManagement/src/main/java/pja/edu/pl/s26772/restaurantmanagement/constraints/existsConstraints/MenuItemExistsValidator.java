package pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pja.edu.pl.s26772.restaurantmanagement.services.MenuItemService;

@Component
public class MenuItemExistsValidator implements ConstraintValidator<MenuItemExists,Long> {
    private MenuItemService menuItemService;

    public MenuItemExistsValidator() {
    }

    @Autowired
    public MenuItemExistsValidator(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @Override
    public void initialize(MenuItemExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return menuItemService.getMenuItem(aLong).isPresent();
    }
}

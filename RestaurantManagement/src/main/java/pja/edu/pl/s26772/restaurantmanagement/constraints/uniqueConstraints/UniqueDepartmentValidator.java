package pja.edu.pl.s26772.restaurantmanagement.constraints.uniqueConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pja.edu.pl.s26772.restaurantmanagement.services.DepartmentService;

@Component
public class UniqueDepartmentValidator implements ConstraintValidator<UniqueDepartment,String> {
    private DepartmentService departmentService;

    public UniqueDepartmentValidator() {
    }

    @Autowired
    public UniqueDepartmentValidator(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void initialize(UniqueDepartment constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !departmentService.getDepartmentByName(s).isPresent();
    }
}

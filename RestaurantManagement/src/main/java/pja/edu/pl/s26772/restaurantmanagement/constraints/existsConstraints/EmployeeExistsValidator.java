package pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pja.edu.pl.s26772.restaurantmanagement.services.EmployeeService;

@Component
public class EmployeeExistsValidator implements ConstraintValidator<EmployeeExists,Long> {
    private EmployeeService employeeService;

    public EmployeeExistsValidator() {
    }

    @Autowired
    public EmployeeExistsValidator(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void initialize(EmployeeExists constraintAnnotation) {}

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return employeeService.getEmployee(aLong).isPresent();
    }
}

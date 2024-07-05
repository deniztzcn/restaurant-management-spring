package pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pja.edu.pl.s26772.restaurantmanagement.services.CustomerService;

@Component
public class CustomerExistsValidator implements ConstraintValidator<CustomerExists,Long> {
    private CustomerService customerService;

    public CustomerExistsValidator() {
    }

    @Autowired
    public CustomerExistsValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void initialize(CustomerExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return customerService.getCustomer(aLong).isPresent();
    }
}

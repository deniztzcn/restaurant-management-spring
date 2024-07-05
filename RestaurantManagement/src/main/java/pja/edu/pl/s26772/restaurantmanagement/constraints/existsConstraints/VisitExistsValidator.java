package pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pja.edu.pl.s26772.restaurantmanagement.services.VisitService;

@Component
public class VisitExistsValidator implements ConstraintValidator<VisitExists,Long> {
    private VisitService visitService;

    @Autowired
    public VisitExistsValidator(VisitService visitService) {
        this.visitService = visitService;
    }

    public VisitExistsValidator() {
    }

    @Override
    public void initialize(VisitExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return visitService.getVisitById(aLong).isPresent();
    }
}

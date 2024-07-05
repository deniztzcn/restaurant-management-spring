package pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pja.edu.pl.s26772.restaurantmanagement.services.TableService;

@Component
public class TableExistsValidator implements ConstraintValidator<TableExists,Long> {
    private TableService tableService;

    @Autowired
    public TableExistsValidator(TableService tableService) {
        this.tableService = tableService;
    }

    public TableExistsValidator() {
    }

    @Override
    public void initialize(TableExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return tableService.getTable(aLong).isPresent();
    }
}

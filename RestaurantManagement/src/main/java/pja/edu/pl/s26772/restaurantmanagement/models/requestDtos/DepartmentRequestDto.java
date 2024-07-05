package pja.edu.pl.s26772.restaurantmanagement.models.requestDtos;

import jakarta.validation.constraints.NotEmpty;
import pja.edu.pl.s26772.restaurantmanagement.constraints.uniqueConstraints.UniqueDepartment;

public class DepartmentRequestDto {
    @NotEmpty
    @UniqueDepartment
    private String name;

    public DepartmentRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

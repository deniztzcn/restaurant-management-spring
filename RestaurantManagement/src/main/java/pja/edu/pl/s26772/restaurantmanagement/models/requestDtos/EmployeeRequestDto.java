package pja.edu.pl.s26772.restaurantmanagement.models.requestDtos;

import jakarta.validation.constraints.NotEmpty;
import pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints.EmployeeExists;

public class EmployeeRequestDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String departmentName;

    @EmployeeExists
    private Long supervisorId;

    public EmployeeRequestDto() {
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}

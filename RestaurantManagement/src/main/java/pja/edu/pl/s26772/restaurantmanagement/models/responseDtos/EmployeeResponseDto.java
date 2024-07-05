package pja.edu.pl.s26772.restaurantmanagement.models.responseDtos;

import java.util.List;

public class EmployeeResponseDto {
    private String name;
    private String surname;
    private EmployeeResponseDto supervisor;
    private String departmentName;

    public EmployeeResponseDto() {
    }

    public EmployeeResponseDto getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(EmployeeResponseDto supervisor) {
        this.supervisor = supervisor;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

}

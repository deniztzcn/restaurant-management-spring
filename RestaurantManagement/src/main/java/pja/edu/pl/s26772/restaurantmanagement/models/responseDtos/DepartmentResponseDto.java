package pja.edu.pl.s26772.restaurantmanagement.models.responseDtos;

import java.util.List;

public class DepartmentResponseDto {
    private Long departmentId;
    private String name;
    private List<EmployeeResponseDto> employees;

    public DepartmentResponseDto() {
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeResponseDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResponseDto> employees) {
        this.employees = employees;
    }
}

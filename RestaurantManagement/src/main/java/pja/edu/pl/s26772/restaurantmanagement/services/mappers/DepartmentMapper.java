package pja.edu.pl.s26772.restaurantmanagement.services.mappers;

import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Department;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.DepartmentRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.DepartmentResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.EmployeeResponseDto;

import java.util.List;

@Service
public class DepartmentMapper {
    private final EmployeeMapper employeeMapper;

    public DepartmentMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public DepartmentResponseDto departmentToResponseDto(Department department){
        DepartmentResponseDto responseDto = new DepartmentResponseDto();
        responseDto.setDepartmentId(department.getId());
        responseDto.setName(department.getName());
        List<EmployeeResponseDto> employees = department.getEmployees().stream().map(employeeMapper::employeeToResponseDto).toList();
        responseDto.setEmployees(employees);
        return responseDto;
    }

    public DepartmentRequestDto departmentToRequestDto(Department department){
        DepartmentRequestDto dto = new DepartmentRequestDto();
        dto.setName(department.getName());
        return dto;
    }

    public Department requestDtoToDepartment(DepartmentRequestDto requestDto){
        Department department = new Department();
        department.setName(requestDto.getName());
        return department;
    }
}

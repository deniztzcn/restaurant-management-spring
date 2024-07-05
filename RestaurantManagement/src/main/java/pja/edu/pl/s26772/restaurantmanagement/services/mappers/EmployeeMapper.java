package pja.edu.pl.s26772.restaurantmanagement.services.mappers;

import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Employee;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.EmployeeRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.EmployeeResponseDto;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeMapper {

    //IF YOU HAVE TIME CHECK THE CASE WHERE WE SHOW THE SUPERVISED EMPLOYEES IN RESPONSE DTO AS WELL
    public EmployeeResponseDto employeeToResponseDto(Employee employee){
        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        responseDto.setName(employee.getName());
        responseDto.setSurname(employee.getSurname());
        if (employee.getSupervisor() != null) {
            EmployeeResponseDto supervisorDto = employeeToResponseDto(employee.getSupervisor());
            responseDto.setSupervisor(supervisorDto);
        } else {
            responseDto.setSupervisor(null);
        }

        responseDto.setDepartmentName(employee.getDepartment().getName());
        return responseDto;
    }

    public Employee requestToEmployee(EmployeeRequestDto requestDto){
        Employee employee = new Employee();
        employee.setName(requestDto.getName());
        employee.setSurname(requestDto.getSurname());
        return employee;
    }

    public EmployeeRequestDto employeeToRequestDto(Employee employee) {
        EmployeeRequestDto requestDto = new EmployeeRequestDto();
        requestDto.setName(employee.getName());
        requestDto.setSurname(employee.getSurname());
        requestDto.setDepartmentName(employee.getDepartment().getName());
        if(employee.getSupervisor() != null){
            requestDto.setSupervisorId(employee.getSupervisor().getId());
        } else {
            requestDto.setSupervisorId(null);
        }

        return requestDto;
    }
}

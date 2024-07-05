package pja.edu.pl.s26772.restaurantmanagement.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Department;
import pja.edu.pl.s26772.restaurantmanagement.models.Employee;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.EmployeeRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.EmployeeResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.DepartmentRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.EmployeeRepository;
import pja.edu.pl.s26772.restaurantmanagement.services.mappers.EmployeeMapper;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.departmentRepository = departmentRepository;
    }

    public List<EmployeeResponseDto> getAllEmployees(){
        List<Employee> employees = employeeRepository.getAllEmployees();
        return employees.stream().map(employeeMapper::employeeToResponseDto).toList();
    }

    @Transactional
    public EmployeeResponseDto addEmployee(EmployeeRequestDto requestDto){
        Employee employee = employeeMapper.requestToEmployee(requestDto);
        try{
            Department department = departmentRepository.getDepartmentByName(requestDto.getDepartmentName()).orElseThrow();
            employee.setDepartment(department);
        }catch(NoSuchElementException ex){
            Department department= new Department();
            department.setName(requestDto.getDepartmentName());
            employee.setDepartment(department);
        }
        try{
            Employee supervisor = getEmployee(requestDto.getSupervisorId()).orElseThrow();
            employee.setSupervisor(supervisor);
        }catch(NoSuchElementException ex){
            employee.setSupervisor(null);
        }
        return employeeMapper.employeeToResponseDto(employeeRepository.save(employee));
    }

    public Optional<Employee> getEmployee(Long id){
        return employeeRepository.findById(id);
    }

    public EmployeeResponseDto getResponseDto(Employee employee){
        return employeeMapper.employeeToResponseDto(employee);
    }

    @Transactional
    public void deleteEmployee(Employee employee) {
        try{
            List<Employee> employees = employeeRepository.getSupervisedEmployees(employee);
            for(Employee tempEmp : employees){
                tempEmp.setSupervisor(null);
                employeeRepository.save(tempEmp);
            }
            employeeRepository.delete(employee);
        } catch(Exception ex){
            System.out.println("smth happened");
        }
    }

    public EmployeeRequestDto getRequestDto(Employee employee) {
        return employeeMapper.employeeToRequestDto(employee);
    }

    @Transactional
    public void updateEmployee(Employee employee, EmployeeRequestDto patchedDto) {
        try{
            Department department = departmentRepository.getDepartmentByName(patchedDto.getDepartmentName()).orElseThrow();
            employee.setDepartment(department);
        }catch(NoSuchElementException ex){
            Department department = new Department();
            department.setName(patchedDto.getDepartmentName());
            employee.setDepartment(department);
        }
        try{
            Employee supervisor = getEmployee(patchedDto.getSupervisorId()).orElseThrow();
            employee.setSupervisor(supervisor);
        }catch(NoSuchElementException ex){
            employee.setSupervisor(null);
        }
        employee.setName(patchedDto.getName());
        employee.setSurname(patchedDto.getSurname());
    }
}

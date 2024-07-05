package pja.edu.pl.s26772.restaurantmanagement.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Category;
import pja.edu.pl.s26772.restaurantmanagement.models.Department;
import pja.edu.pl.s26772.restaurantmanagement.models.Employee;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.CategoryRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.DepartmentRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.CategoryResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.DepartmentResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.EmployeeResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MenuItemResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.DepartmentRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.EmployeeRepository;
import pja.edu.pl.s26772.restaurantmanagement.services.mappers.DepartmentMapper;
import pja.edu.pl.s26772.restaurantmanagement.services.mappers.EmployeeMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper,
                             EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
        this.employeeRepository = employeeRepository;
    }

    public List<DepartmentResponseDto> getDepartmentWithEmployees() {
        List<Department> departments = departmentRepository.getAllDepartments();

        return departments.stream().map(departmentMapper::departmentToResponseDto).toList();
    }

    public Optional<Department> getDepartmentByName(String deptName){
        return departmentRepository.getDepartmentByName(deptName);
    }

    public DepartmentResponseDto getResponseDto(Department department){
        return departmentMapper.departmentToResponseDto(department);
    }

    public DepartmentRequestDto getRequestDto(Department department){
        return departmentMapper.departmentToRequestDto(department);
    }

    @Transactional
    public DepartmentResponseDto addDepartment(DepartmentRequestDto requestDto){
        return departmentMapper.departmentToResponseDto(departmentRepository.save(departmentMapper.requestDtoToDepartment(requestDto)));
    }

    public Optional<Department> getDepartment(Long departmentId)
    {
        return departmentRepository.findById(departmentId);
    }

    @Transactional
    public void deleteDepartment(Department department) {
        List<Employee> employees = employeeRepository.findByDepartmentId(department.getId());
        for (Employee employee : employees) {
            employee.setDepartment(null);
            employeeRepository.save(employee);
        }
        departmentRepository.delete(department);

    }

    @Transactional
    public void updateDepartment(Department department, DepartmentRequestDto patchedDto) {
        department.setName(patchedDto.getName());
        departmentRepository.save(department);
    }
}

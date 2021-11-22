package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.employee.EmployeeRequestDto;
import ua.com.alevel.dto.employee.EmployeeResponseDto;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.facade.EmployeeFacade;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeFacadeImpl implements EmployeeFacade {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeFacadeImpl(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @Override
    public void create(EmployeeRequestDto employeeRequestDto) {
        Department department = departmentService.findById(employeeRequestDto.getDepartmentId());
        if (department != null) {
            Employee employee = new Employee();
            employee.setDepartment(department);
            employee.setFirstName(employeeRequestDto.getFirstName());
            employee.setLastName(employeeRequestDto.getLastName());
            employee.setAge(employeeRequestDto.getAge());
            employeeService.create(employee);
        }
    }

    @Override
    public void update(EmployeeRequestDto employeeRequestDto, Long id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            employee.setFirstName(employeeRequestDto.getFirstName());
            employee.setLastName(employee.getLastName());
            employee.setAge(employeeRequestDto.getAge());
            employeeService.update(employee);
        }
    }

    @Override
    public void delete(Long id) {
        employeeService.delete(id);
    }

    @Override
    public EmployeeResponseDto findById(Long id) {
        return new EmployeeResponseDto(employeeService.findById(id));
    }

    @Override
    public List<EmployeeResponseDto> findAll() {
        return generateDtoListByEntities(employeeService.findAll());
    }

    @Override
    public List<EmployeeResponseDto> findAllByDepartment(Long departmentId) {
        return generateDtoListByEntities(employeeService.findAllByDepartment(departmentId));
    }

    private List<EmployeeResponseDto> generateDtoListByEntities(List<Employee> list) {
        return list.stream()
                .map(EmployeeResponseDto::new)
                .collect(Collectors.toList());
    }
}

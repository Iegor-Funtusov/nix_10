package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.department.DepartmentRequestDto;
import ua.com.alevel.dto.department.DepartmentResponseDto;
import ua.com.alevel.entity.Department;
import ua.com.alevel.facade.DepartmentFacade;
import ua.com.alevel.service.DepartmentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentFacadeImpl implements DepartmentFacade {

    private final DepartmentService departmentService;

    public DepartmentFacadeImpl(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void create(DepartmentRequestDto departmentRequestDto) {
        Department department = new Department();
        department.setDepartmentType(departmentRequestDto.getDepartmentType());
        department.setName(departmentRequestDto.getName());
        departmentService.create(department);
    }

    @Override
    public void update(DepartmentRequestDto departmentRequestDto, Long id) {
        Optional<Department> departmentOptional = departmentService.findById(id);
        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            department.setDepartmentType(departmentRequestDto.getDepartmentType());
            department.setName(departmentRequestDto.getName());
            departmentService.update(department);
        }
    }

    @Override
    public void delete(Long id) {
        departmentService.delete(id);
    }

    @Override
    public DepartmentResponseDto findById(Long id) {
        Optional<Department> departmentOptional = departmentService.findById(id);
        return departmentOptional.map(DepartmentResponseDto::new).orElse(null);
    }

    @Override
    public List<DepartmentResponseDto> findAll() {
        return departmentService.findAll()
                .stream()
                .map(DepartmentResponseDto::new)
                .collect(Collectors.toList());
    }
}

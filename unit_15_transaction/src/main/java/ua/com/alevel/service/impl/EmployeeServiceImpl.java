package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.persistence.repository.EmployeeRepository;
import ua.com.alevel.service.EmployeeService;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final CrudRepositoryHelper<Employee, EmployeeRepository> crudRepositoryHelper;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(
            CrudRepositoryHelper<Employee, EmployeeRepository> crudRepositoryHelper,
            EmployeeRepository employeeRepository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void create(Employee entity) {
        crudRepositoryHelper.create(employeeRepository, entity);
    }

    @Override
    public void update(Employee entity) {
        crudRepositoryHelper.update(employeeRepository, entity);
    }

    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(employeeRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        return crudRepositoryHelper.findById(employeeRepository, id);
    }

    @Override
    public DataTableResponse<Employee> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(employeeRepository, request);
    }
}

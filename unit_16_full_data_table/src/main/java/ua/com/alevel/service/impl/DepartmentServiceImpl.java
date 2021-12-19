package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.repository.DepartmentRepository;
import ua.com.alevel.service.DepartmentService;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final CrudRepositoryHelper<Department, DepartmentRepository> crudRepositoryHelper;
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(
            CrudRepositoryHelper<Department, DepartmentRepository> crudRepositoryHelper,
            DepartmentRepository departmentRepository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void create(Department entity) {
        crudRepositoryHelper.create(departmentRepository, entity);
    }

    @Override
    public void update(Department entity) {
        crudRepositoryHelper.update(departmentRepository, entity);
    }

    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(departmentRepository, id);
    }

    @Override
    public Optional<Department> findById(Long id) {
        return crudRepositoryHelper.findById(departmentRepository, id);
    }

    @Override
    @Transactional()
    public DataTableResponse<Department> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(departmentRepository, request);
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void help() {
        System.out.println("DepartmentServiceImpl.help");
        crudRepositoryHelper.help();
        crudRepositoryHelper.help();
        crudRepositoryHelper.help();
        crudRepositoryHelper.help();
    }
}

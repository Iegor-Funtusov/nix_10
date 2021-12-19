package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.EntityNotFoundException;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Department;
import ua.com.alevel.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeDao employeeDao;
    private final DepartmentDao departmentDao;

    public DepartmentServiceImpl(EmployeeDao employeeDao, DepartmentDao departmentDao) {
        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
    }

    @Override
    public void create(Department entity) {
        departmentDao.create(entity);
    }

    @Override
    public void update(Department entity) {
        if (departmentDao.existById(entity.getId())) {
            departmentDao.update(entity);
        }
    }

    @Override
    public void delete(Long id) {
        if (departmentDao.existById(id)) {
            employeeDao.deleteAllByDepartmentId(id);
            departmentDao.delete(id);
        }
    }

    @Override
    public Department findById(Long id) {
        Department byId = departmentDao.findById(id);
        if (byId == null) {
            throw new EntityNotFoundException("not found, please ...");
        }
        return byId;
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}

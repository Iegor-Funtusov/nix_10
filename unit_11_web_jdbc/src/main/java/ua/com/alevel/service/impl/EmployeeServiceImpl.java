package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.service.EmployeeService;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final DepartmentDao departmentDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao, DepartmentDao departmentDao) {
        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
    }

    @Override
    public void create(Employee entity) {
        employeeDao.create(entity);
    }

    @Override
    public void update(Employee entity) {
        if (employeeDao.existById(entity.getId())) {
            employeeDao.update(entity);
        }
    }

    @Override
    public void delete(Long id) {
        if (employeeDao.existById(id)) {
            employeeDao.delete(id);
        }
    }

    @Override
    public Employee findById(Long id) {
        return employeeDao.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> findAllByDepartment(Long departmentId) {
        if (departmentDao.existById(departmentId)) {
            return employeeDao.findAllByDepartmentId(departmentId);
        }
        return Collections.emptyList();
    }
}

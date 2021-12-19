package ua.com.alevel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.type.DepartmentType;

import java.util.List;

@SpringBootTest
class DataTableJpaApplicationTests {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    void contextLoads() { }

    @Test
    void crud() {
        for (int i = 0; i < 5; i++) {
            Department department = new Department();
            department.setDepartmentType(DepartmentType.JAVA);
            department.setName("dep" + (i + 1));
            departmentDao.create(department);
        }

        Department department = departmentDao.findById(2L);
        Assertions.assertNotNull(department);
        department.setDepartmentType(DepartmentType.JAVA);
        departmentDao.update(department);
        department = departmentDao.findById(2L);
        Assertions.assertEquals(department.getDepartmentType(), DepartmentType.JAVA);

//        departmentDao.delete(3L);
//        boolean dell = departmentDao.existById(3L);
//        Assertions.assertFalse(dell);
//
//        department = departmentDao.findById(4L);
//        departmentDao.delete(department);
//        Assertions.assertFalse(departmentDao.existById(4L));

        List<Department> departments = departmentDao.findAll(1, 5, "name", "desc");
        Assertions.assertNotNull(departments);
        Assertions.assertEquals(5, departments.size());

        department = departmentDao.findById(5L);
        Employee employee = new Employee();
        employee.setAge(35);
        employee.setFirstName("name31");
        employee.setLastName("name32");
        department.addEmployee(employee);
        employee = new Employee();
        employee.setAge(25);
        employee.setFirstName("name41");
        employee.setLastName("name42");
        department.addEmployee(employee);

        departmentDao.update(department);
    }
}

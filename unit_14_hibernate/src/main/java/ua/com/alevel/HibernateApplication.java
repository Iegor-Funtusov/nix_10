package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ua.com.alevel.persistence.dao.DepartmentDao;
import ua.com.alevel.persistence.dao.EmployeeDao;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.persistence.type.DepartmentType;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class })
public class HibernateApplication {

    private final DepartmentDao departmentDao;
    private final EmployeeDao employeeDao;

    public HibernateApplication(DepartmentDao departmentDao, EmployeeDao employeeDao) {
        this.departmentDao = departmentDao;
        this.employeeDao = employeeDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test() {
//        createDepartment();
//        createNotLinkEmployee();
//        attachEmployeeToDepartment();
//        attachDepartmentToEmployee();
//        createEmployeeByDepartment();
        deAttachDepartmentToEmployee();
    }

    private void createDepartment() {
        Department department1 = new Department();
        department1.setDepartmentType(DepartmentType.DEV_OPS);
        department1.setName("dep1");
        departmentDao.create(department1);

        Department department2 = new Department();
        department2.setDepartmentType(DepartmentType.JAVA);
        department2.setName("dep2");
        departmentDao.create(department2);
    }

    private void createNotLinkEmployee() {
        Employee employee1 = new Employee();
        employee1.setAge(18);
        employee1.setFirstName("name1");
        employee1.setLastName("name2");
        employeeDao.create(employee1);

        Employee employee2 = new Employee();
        employee2.setAge(22);
        employee2.setFirstName("name2");
        employee2.setLastName("name2");
        employeeDao.create(employee2);

        Employee employee3 = new Employee();
        employee3.setAge(27);
        employee3.setFirstName("name3");
        employee3.setLastName("name3");
        employeeDao.create(employee3);
    }

    private void attachEmployeeToDepartment() {
        Department department = departmentDao.findById(1L);
        Employee employee = employeeDao.findById(4L);
        Set<Employee> set = department.getEmployees();
        set.add(employee);
        department.setEmployees(set);
        departmentDao.update(department);
    }

    private void attachDepartmentToEmployee() {
        Department department = departmentDao.findById(2L);
        Employee employee = employeeDao.findById(2L);
        Set<Department> set = new HashSet<>();
        set.add(department);
        employee.setDepartments(set);
        employeeDao.update(employee);
    }

    private void createEmployeeByDepartment() {
        Department department = departmentDao.findById(2L);

        Employee employee4 = new Employee();
        employee4.setAge(32);
        employee4.setFirstName("name4");
        employee4.setLastName("name4");

        Set<Employee> set = department.getEmployees();
        set.add(employee4);
        department.setEmployees(set);
        departmentDao.update(department);
    }

    private void deAttachDepartmentToEmployee() {
        Department department = departmentDao.findById(2L);
        Employee employee = employeeDao.findById(4L);
        Set<Employee> set = department.getEmployees();
        set.remove(employee);
        departmentDao.update(department);
    }
}

package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.persistence.repository.DepartmentRepository;
import ua.com.alevel.persistence.type.DepartmentType;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;

import java.util.*;

@SpringBootApplication
public class TransactionApplication {
    
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final DepartmentRepository departmentRepository;

    public TransactionApplication(DepartmentService departmentService, EmployeeService employeeService, DepartmentRepository departmentRepository) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.departmentRepository = departmentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
//        createDepartment();
//        createNotLinkEmployee();
//        attachEmployeeToDepartment();
//        attachDepartmentToEmployee();
//        createEmployeeByDepartment();
//        deAttachDepartmentToEmployee();
//        find();
        testSelected();
    }

    private void testSelected() {
        List<Department> departments = departmentRepository.findByDepartmentTypeOrderByIdDesc(DepartmentType.JAVA);
        System.out.println("departments = " + departments.size());
        departments = departmentRepository.findByDepartmentTypeInOrderByIdDesc(Arrays.asList(DepartmentType.DEV_OPS, DepartmentType.KOTLIN));
        System.out.println("departments = " + departments.size());
        departments = departmentRepository.findByDepartmentTypeInAndNameEndingWithIgnoreCase(Arrays.asList(DepartmentType.DEV_OPS, DepartmentType.KOTLIN), "1");
        System.out.println("departments = " + departments.size());
        for (Department department : departments) {
            System.out.println("department = " + department.getName());
        }
        Employee employee = employeeService.findById(2L).get();
        System.out.println("employee = " + employee.getId());
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);
        departments = departmentRepository.findByEmployees(employees);
        System.out.println("departments = " + departments.size());
        for (Department department : departments) {
            System.out.println("department = " + department.getName());
        }
        System.out.println();
        departments = departmentRepository.findByEmployeesIds(Arrays.asList(1L,2L,10L));
        System.out.println("departments = " + departments.size());
        for (Department department : departments) {
            System.out.println("department = " + department.getName());
        }
    }

    private void createDepartment() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int i1 = random.nextInt(4);
            DepartmentType value = DepartmentType.values()[i1];
            Department department = new Department();
            department.setDepartmentType(value);
            department.setName("dep" + (i + 3));
            departmentService.create(department);
        }

//        Department department1 = new Department();
//        department1.setDepartmentType(DepartmentType.JS);
//        department1.setName("dep3");
//        departmentService.create(department1);
//
//        Department department2 = new Department();
//        department2.setDepartmentType(DepartmentType.KOTLIN);
//        department2.setName("dep4");
//        departmentService.create(department2);
    }

    private void createNotLinkEmployee() {
        Employee employee1 = new Employee();
        employee1.setAge(18);
        employee1.setFirstName("name5");
        employee1.setLastName("name5");
        employeeService.create(employee1);

        Employee employee2 = new Employee();
        employee2.setAge(22);
        employee2.setFirstName("name6");
        employee2.setLastName("name6");
        employeeService.create(employee2);

        Employee employee3 = new Employee();
        employee3.setAge(27);
        employee3.setFirstName("name7");
        employee3.setLastName("name7");
        employeeService.create(employee3);
    }

    private void attachEmployeeToDepartment() {
        Department department = departmentService.findById(1L).get();
        Employee employee = employeeService.findById(2L).get();
        Set<Employee> set = department.getEmployees();
        set.add(employee);
        department.setEmployees(set);
        departmentService.update(department);
    }

    private void attachDepartmentToEmployee() {
        Department department = departmentService.findById(2L).get();
        Employee employee = employeeService.findById(2L).get();
        Set<Department> set = new HashSet<>();
        set.add(department);
        employee.setDepartments(set);
        employeeService.update(employee);
    }

    private void createEmployeeByDepartment() {
        Department department = departmentService.findById(2L).get();

        Employee employee4 = new Employee();
        employee4.setAge(32);
        employee4.setFirstName("name4");
        employee4.setLastName("name4");

        Set<Employee> set = department.getEmployees();
        set.add(employee4);
        department.setEmployees(set);
        departmentService.update(department);
    }

    private void deAttachDepartmentToEmployee() {
        Department department = departmentService.findById(2L).get();
        Employee employee = employeeService.findById(4L).get();
        Set<Employee> set = department.getEmployees();
        set.remove(employee);
        departmentService.update(department);
    }

    private void find() {
        DataTableRequest request = new DataTableRequest();
        request.setSort("id");
        request.setOrder("desc");
        request.setPage(1);
        request.setSize(10);

        DataTableResponse<Department> departments = departmentService.findAll(request);
        DataTableResponse<Employee> employees = employeeService.findAll(request);

        System.out.println("departments = " + departments);
        System.out.println();
        System.out.println("employees = " + employees);
    }
}

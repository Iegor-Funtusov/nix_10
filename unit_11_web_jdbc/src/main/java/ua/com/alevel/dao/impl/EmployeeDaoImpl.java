package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.type.DepartmentType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeDaoImpl implements EmployeeDao {

    private final JpaConfig jpaConfig;

    public EmployeeDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    private final static String CREATE_EMPLOYEE_QUERY = "insert into employees values (default,?,?,?,?,?,?)";
    private final static String UPDATE_EMPLOYEE_QUERY = "update employees set age = ?, first_name = ?, last_name = ?, updated = ? where id = ";
    private final static String DELETE_EMPLOYEE_BY_ID_QUERY = "delete from employees where id = ";
    private final static String DELETE_EMPLOYEE_BY_DEPARTMENT_QUERY = "delete from employees where department_id = ";
    private final static String EXIST_EMPLOYEE_BY_ID_QUERY = "select count(*) from employees where id = ";
    private final static String FIND_EMPLOYEE_BY_ID_QUERY = "select * from employees as emp join departments as dep on dep.id = emp.department_id where emp.id = ";
    private final static String FIND_ALL_EMPLOYEE_QUERY = "select * from employees as emp join departments as dep on dep.id = emp.department_id";
    private final static String FIND_ALL_EMPLOYEE_BY_DEPARTMENT_QUERY = "select * from employees as emp join departments as dep on dep.id = emp.department_id where emp.department_id = ";

    @Override
    public void create(Employee entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_EMPLOYEE_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setInt(3, entity.getAge());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getLastName());
            preparedStatement.setLong(6, entity.getDepartment().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_EMPLOYEE_QUERY + entity.getId())) {
            preparedStatement.setInt(1, entity.getAge());
            preparedStatement.setString(2, entity.getFirstName());
            preparedStatement.setString(3, entity.getLastName());
            preparedStatement.setTimestamp(4, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_EMPLOYEE_BY_ID_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_EMPLOYEE_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    @Override
    public Employee findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_EMPLOYEE_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                return convertResultSetToEmployee(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_EMPLOYEE_QUERY)) {
            while (resultSet.next()) {
                employees.add(convertResultSetToEmployee(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void deleteAllByDepartmentId(Long departmentId) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_EMPLOYEE_BY_DEPARTMENT_QUERY + departmentId)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> findAllByDepartmentId(Long departmentId) {
        List<Employee> employees = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_EMPLOYEE_BY_DEPARTMENT_QUERY + departmentId)) {
            while (resultSet.next()) {
                employees.add(convertResultSetToEmployee(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private Employee convertResultSetToEmployee(ResultSet resultSet) throws SQLException {
        Long employeeId = resultSet.getLong("emp.id");
        Timestamp employeeCreated = resultSet.getTimestamp("emp.created");
        Timestamp employeeUpdated = resultSet.getTimestamp("emp.updated");
        int age = resultSet.getInt("age");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");

        Long departmentId = resultSet.getLong("dep.id");
        Timestamp departmentCreated = resultSet.getTimestamp("dep.created");
        Timestamp departmentUpdated = resultSet.getTimestamp("dep.updated");
        String type = resultSet.getString("department_type");
        String name = resultSet.getString("name");
        Department department = new Department();
        department.setId(departmentId);
        department.setName(name);
        department.setDepartmentType(DepartmentType.valueOf(type));
        department.setCreated(new Date(departmentCreated.getTime()));
        department.setUpdated(new Date(departmentUpdated.getTime()));

        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setAge(age);
        employee.setCreated(new Date(employeeCreated.getTime()));
        employee.setUpdated(new Date(employeeUpdated.getTime()));
        employee.setDepartment(department);
        return employee;
    }
}

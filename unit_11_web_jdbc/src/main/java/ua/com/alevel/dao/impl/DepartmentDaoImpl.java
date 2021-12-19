package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.entity.Department;
import ua.com.alevel.type.DepartmentType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DepartmentDaoImpl implements DepartmentDao {

    private final JpaConfig jpaConfig;

    public DepartmentDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    private final static String CREATE_DEPARTMENT_QUERY = "insert into departments values (default,?,?,?,?)";
    private final static String UPDATE_DEPARTMENT_QUERY = "update departments set name = ?, department_type = ?, updated = ? where id = ";
    private final static String DELETE_DEPARTMENT_BY_ID_QUERY = "delete from departments where id = ";
    private final static String EXIST_DEPARTMENT_BY_ID_QUERY = "select count(*) from departments where id = ";
    private final static String FIND_DEPARTMENT_BY_ID_QUERY = "select * from departments where id = ";
    private final static String FIND_ALL_DEPARTMENT_QUERY = "select * from departments";

    @Override
    public void create(Department entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_DEPARTMENT_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setString(3, entity.getDepartmentType().name());
            preparedStatement.setString(4, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Department entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_DEPARTMENT_QUERY + entity.getId())) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDepartmentType().name());
            preparedStatement.setTimestamp(3, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_DEPARTMENT_BY_ID_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_DEPARTMENT_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    @Override
    public Department findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_DEPARTMENT_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                return convertResultSetToDepartment(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_DEPARTMENT_QUERY)) {
            while (resultSet.next()) {
                departments.add(convertResultSetToDepartment(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    private Department convertResultSetToDepartment(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        String type = resultSet.getString("department_type");
        String name = resultSet.getString("name");
        Department department = new Department();
        department.setId(id);
        department.setName(name);
        department.setDepartmentType(DepartmentType.valueOf(type));
        department.setCreated(new Date(created.getTime()));
        department.setUpdated(new Date(updated.getTime()));
        return department;
    }
}

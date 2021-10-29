package ua.com.alevel.dao.impl;

import com.nixsolutions.dinix.annotations.Service;
import com.nixsolutions.dinix.db.EntityManager;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;

import java.sql.*;
import java.util.*;

@Service
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    private static final String CREATE_USER = "insert into users values(default,?,?,?)";
    private static final String UPDATE_USER_BY_ID = "update users set name = ?, age = ? where id = ";
    private static final String DELETE_USER_BY_ID = "delete from users where id = ";
    private static final String FIND_USER_BY_ID = "select * from users where id = ";
    private static final String FIND_ALL_USERS = "select * from users";

    @Override
    public void create(User entity) {
        try(PreparedStatement preparedStatement = entityManager.getConnection().prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setInt(3, entity.getAge());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("problem = " + e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        try(PreparedStatement preparedStatement = entityManager.getConnection().prepareStatement(UPDATE_USER_BY_ID + entity.getId())) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem = " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement preparedStatement = entityManager.getConnection().prepareStatement(DELETE_USER_BY_ID + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem = " + e.getMessage());
        }
    }

    @Override
    public User findById(int id) {
        try(ResultSet resultSet = entityManager.getStatement().executeQuery(FIND_USER_BY_ID + id)) {
            while (resultSet.next()) {
                return generateUserByResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<User> findAll() {
        List<User> users = new ArrayList<>();
        try(ResultSet resultSet = entityManager.getStatement().executeQuery(FIND_ALL_USERS)) {
            while (resultSet.next()) {
                users.add(generateUserByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean existByEmail(String email) {
        return false;
    }

    private User generateUserByResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        int age = resultSet.getInt("age");
        int id = resultSet.getInt("id");
        User user = new User();
        user.setId(id);
        user.setAge(age);
        user.setEmail(email);
        user.setName(name);
        return user;
    }
}

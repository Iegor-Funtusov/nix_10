package ua.com.alevel.dao.impl;

import com.nixsolutions.dinix.annotations.Service;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;

import java.util.Collection;

@Service
public class UserDaoImpl implements UserDao {

    @Override
    public void create(User entity) {
        System.out.println("UserDaoImpl.create");
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public Collection<User> findAll() {
        System.out.println("UserDaoImpl.findAll");
        return null;
    }

    @Override
    public boolean existByEmail(String email) {
        return false;
    }
}

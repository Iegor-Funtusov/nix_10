package ua.com.alevel.service.impl;

import com.nixsolutions.dinix.annotations.Autowired;
import com.nixsolutions.dinix.annotations.Service;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void create(User entity) {
        System.out.println("UserServiceImpl.create");
        if (!userDao.existByEmail(entity.getEmail())) {
            userDao.create(entity);
        } else {
            System.out.println("user exist by email");
        }
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public Collection<User> findAll() {
        System.out.println("UserServiceImpl.findAll");
        return userDao.findAll();
    }
}

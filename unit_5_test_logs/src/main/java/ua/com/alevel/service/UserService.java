package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;

import java.util.List;

public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final UserDao userDao = new UserDao();

    public void create(User user) {
        LOGGER_INFO.info("start create user");
        if (!userDao.existByEmail(user.getEmail())) {
            userDao.create(user);
            LOGGER_INFO.info("finish create user");
        } else {
            System.out.println("user exist by email");
            LOGGER_WARN.warn("user exist by email: " + user.getEmail());
        }
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(String id) {
        userDao.delete(id);
    }

    public User findById(String id) {
        return userDao.findById(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}

package ua.com.alevel.dao.impl;

import ua.com.alevel.config.ActiveClass;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.db.UserDB;
import ua.com.alevel.db.impl.MySqlDb;
import ua.com.alevel.db.impl.OracleDb;
import ua.com.alevel.entity.User;
import ua.com.alevel.util.ResourcesUtil;

import java.util.Collection;
import java.util.Map;

@ActiveClass
public class UserDaoImpl implements UserDao {

    private final UserDB db;

    public UserDaoImpl() {
        Map<String, String> map = ResourcesUtil.getResources(this.getClass().getClassLoader());
        String dbProps = map.get("db");
        if (dbProps.equals("oracle")) {
            db = OracleDb.getInstance();
        } else {
            db = MySqlDb.getInstance();
        }
    }

    @Override
    public void create(User entity) {
        db.create(entity);
    }

    @Override
    public void update(User entity) {
        db.update(entity);
    }

    @Override
    public void delete(String id) {
        db.delete(id);
    }

    @Override
    public User findById(String id) {
        return db.findById(id);
    }

    @Override
    public Collection<User> findAll() {
        return db.findAll();
    }

    @Override
    public boolean existByEmail(String email) {
        return db.existByEmail(email);
    }
}

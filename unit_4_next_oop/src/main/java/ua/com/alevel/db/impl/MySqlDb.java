package ua.com.alevel.db.impl;

import ua.com.alevel.db.UserDB;
import ua.com.alevel.entity.User;
import ua.com.alevel.util.GenerateIdUtil;

import java.util.ArrayList;
import java.util.List;

public class MySqlDb implements UserDB {

    private final List<User> users;
    private static MySqlDb instance;

    private MySqlDb() {
        System.out.println("MySqlDb.MySqlDb");
        users = new ArrayList<>();
    }

    public static MySqlDb getInstance() {
        if (instance == null) {
            instance = new MySqlDb();
        }
        return instance;
    }

    public void create(User user) {
        user.setId(GenerateIdUtil.generateId(users));
        users.add(user);
    }

    public void update(User user) {
        User current = findById(user.getId());
        current.setName(user.getName());
        current.setAge(user.getAge());
    }

    public void delete(String id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public User findById(String id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    public List<User> findAll() {
        return users;
    }

    public boolean existByEmail(String email) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
    }
}

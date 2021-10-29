package com.nixsolutions.dinix.db;

import com.nixsolutions.dinix.util.ResourcesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class EntityManager {

    private Connection connection;
    private Statement statement;

    private static EntityManager instance;

    private EntityManager() { }

    public static EntityManager getInstance() {
        if (instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    public void initEntityManager(ClassLoader classLoader) {
        Map<String, String> resources = ResourcesUtil.getResources(classLoader);
        try {
            Class.forName(resources.get("db.driver"));
            connection = DriverManager.getConnection(
                    resources.get("db.url"),
                    resources.get("db.user"),
                    resources.get("db.password"));
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("problem = " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}

package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public Connection getConnection(){
        Connection connection = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "Ms0830151");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}

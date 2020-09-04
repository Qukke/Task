package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alex", "Mart", (byte) 30);
        userService.saveUser("Ain", "Gimat", (byte) 25);
        userService.saveUser("Vict", "Gnash", (byte) 20);
        userService.saveUser("Ivan", "Proh", (byte)35);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}

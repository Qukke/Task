package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("asd", "asd",(byte)1);
        User user = userService.getAllUsers().get(0);
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("asd", "asd",(byte)1);
        userService.removeUserById(1L);

    }
}

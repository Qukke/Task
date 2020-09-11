package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceHibernateImpl();
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

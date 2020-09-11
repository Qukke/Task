package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = Util.getSession();
        Session session = sessionFactory.openSession();
        Transaction rx = session.beginTransaction();
        session.createSQLQuery("DELETE FROM user").executeUpdate();
        rx.commit();
        session.close();
        sessionFactory.close();
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = Util.getSession();
        Session session = sessionFactory.openSession();
        Transaction rx = session.beginTransaction();
        session.createSQLQuery("DROP TABLE `sys`.`user`").executeUpdate();
        rx.commit();
        session.close();
        sessionFactory.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory factory = Util.getSession();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist( new User(name, lastName, age));
        tx.commit();
        System.out.println("User saved");
        session.close();
        factory.close();
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory factory = Util.getSession();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        User deleted = (User) session.get(User.class, id);
        if (deleted != null) {
            session.delete(deleted);
            tx.commit();
            System.out.println("User deleted");
        }
        else {
            System.out.println("Wrong ID");
        }
        session.close();
        factory.close();

    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory session = Util.getSession();
        List<User> out =  (List<User>) session.openSession().createQuery("From User").list();
        session.close();
        return out;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = Util.getSession();
        Session session = sessionFactory.openSession();
        Transaction rx = session.beginTransaction();
        session.createQuery("Delete From user Where").executeUpdate();
        rx.commit();
        session.close();
        sessionFactory.close();
    }
}

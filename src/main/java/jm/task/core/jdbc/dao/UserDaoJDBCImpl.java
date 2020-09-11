package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        PreparedStatement ps = null;
        Connection connection = Util.getConnection();
        ResultSet rs = connection.createStatement().executeQuery("SHOW TABLES LIKE 'user'");
        if (!rs.next()) {
            try {
                ps = connection.prepareStatement( "CREATE TABLE `sys`.`user` (\n" +
                        "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` VARCHAR(45) NULL,\n" +
                        "  `lastName` VARCHAR(45) NULL,\n" +
                        "  `age` INT NULL,\n" +
                        "  PRIMARY KEY (`id`),\n" +
                        "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);\n");
               ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ps.close();
                connection.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        PreparedStatement ps = null;
        Connection connection = Util.getConnection();
        ResultSet rs = connection.createStatement().executeQuery("SHOW TABLES LIKE 'user'");
        if (rs.next()) {
            try {
                ps = connection.prepareStatement("DROP TABLE `sys`.`user`");
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ps.close();
                connection.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = Util.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(id) FROM USER");
            long id = 0L;
            while (rs.next()) {
                id = rs.getLong(1);
            }
            ps = connection.prepareStatement("INSERT INTO USER (id, name, lastName, age) VALUES (?,?,?,?)");
            ps.setLong(1, id+1);
            ps.setString(2,name);
            ps.setString(3,lastName);
            ps.setInt(4, age);
            ps.executeUpdate();
            System.out.println("Пользователь с именем "+name+" добавлен в базу данных");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            assert ps != null;
            ps.close();
            connection.close();
        }
    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = Util.getConnection();
        try {
            ps = connection.prepareStatement("DELETE FROM sys.user WHERE id = ?");
            ps.setLong(1,id);
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            assert ps != null;
            ps.close();
            connection.close();

        }
    }

    public List<User> getAllUsers() {
        List<User> out = new ArrayList<>();
        Connection connection = Util.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM USER");
            while (rs.next()) {
                User outed = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                outed.setId(rs.getLong(1));
                out.add(outed);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return out;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("DELETE FROM user");
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            assert ps != null;
            ps.close();
            connection.close();
        }
    }
}

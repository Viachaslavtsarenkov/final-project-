package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.bean.UserRole;
import by.tsarenkov.shop.bean.UserStatus;
import by.tsarenkov.shop.dao.db.ConnectionPoolException;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.dao.db.ConnectionPool;

import java.sql.*;
import java.util.SimpleTimeZone;

public class SQLUserDAO implements UserDAO {

    private static final String query = "INSERT INTO store.users (username, surname, email, status, password, user_role)"
            + "VALUES (?,?,?,?,? ?)";
    private static final String queryGettingUser = "SELECT username FROM users WHERE email = ?";

    public SQLUserDAO(){

    }

    @Override
    public User authorization(String login, String password) {
        return null;
    }

    @Override
    public boolean registration(UserRegistrationInfo user) {
        Connection connection = null;
        try {
            ConnectionPool pool = new ConnectionPool();
            connection = pool.takeConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, UserStatus.NO_ACTIVATED.toString());
            ps.setString(5, user.getPassword());
            ps.setString(6, UserRole.CUSTOMER.toString());
            ps.executeUpdate();
            pool.closeConnection(connection, ps);
        } catch (ConnectionPoolException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public boolean findUser(String login) {
        try {
            ConnectionPool pool = new ConnectionPool();
            Connection connection = pool.takeConnection();
            PreparedStatement ps = connection.prepareStatement(queryGettingUser);
            ps.setString(1, login);
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            return resultSet.next();
        } catch (SQLException e) {

        } catch (ConnectionPoolException e) {

        }
        return false;
    }
}

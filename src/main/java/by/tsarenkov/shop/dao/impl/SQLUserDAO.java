package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.bean.UserRole;
import by.tsarenkov.shop.dao.db.ConnectionPoolException;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.dao.db.ConnectionPool;

import java.sql.*;

public class SQLUserDAO implements UserDAO {

    public SQLUserDAO(){

    }

    @Override
    public User authorization(String login, String password) {
        return new User();
    }

    @Override
    public boolean registration(UserRegistrationInfo user) {
        Connection con = null;
        try {
            Connection connection  = ConnectionPool.getInstance().takeConnection();
            String query = "INSERT INTO users(name, surname, email, password, role) VALUES(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, UserRole.CUSTOMER.toString());
            ps.executeUpdate();

        } catch (ConnectionPoolException e) {

        } catch (SQLException e) {

        }
        return true;
    }

}

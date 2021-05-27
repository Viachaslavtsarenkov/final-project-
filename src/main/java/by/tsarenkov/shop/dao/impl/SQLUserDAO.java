package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.dao.ConnectionPoolException;
import by.tsarenkov.shop.dao.DBParameter;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.dao.ConnectionPool;

import java.io.File;
import java.sql.*;

public class SQLUserDAO implements UserDAO {

    public SQLUserDAO(){

    }

    @Override
    public User authorization(String login, String password) {
        return new User();
    }

    @Override
    public boolean registration() {
        return false;
    }

    @Override
    public String check() {
        String result = null;
        Connection con = null;
        Statement st = null;
        ResultSet res;
        try {
            Connection connection  = ConnectionPool.getInstance().takeConnection();
            result = connection.getMetaData().getURL();
        } catch (ConnectionPoolException e) {

        }
        catch (SQLException e) {

        }

        return result;
    }
}

package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.dao.ConnectionPool;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.dao.impl.SQLUserDAO;
import by.tsarenkov.shop.service.UserService;

import java.sql.Connection;

public class UserServiceImpl implements UserService {

    public UserServiceImpl() {

    }

    @Override
    public User authorization(String login, String password) {
        return null;
    }

    @Override
    public boolean registration(UserRegistrationInfo user) {
        return false;
    }

    @Override
    public String check() {


        UserDAO user = new SQLUserDAO();
        String result = user.check();
        return result;
    }
}

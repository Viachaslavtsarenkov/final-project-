package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.dao.impl.SQLUserDAO;
import by.tsarenkov.shop.service.UserService;

public class UserServiceImpl implements UserService {

    public UserServiceImpl() {

    }

    @Override
    public User authorization(String login, String password) {
        return null;
    }

    @Override
    public boolean registration(UserRegistrationInfo user) {
        UserDAO userDAO = new SQLUserDAO();
        try {
            boolean result = userDAO.registration(user);
        } catch (DAOException e) {
            //*
        }

        return true;
    }


}

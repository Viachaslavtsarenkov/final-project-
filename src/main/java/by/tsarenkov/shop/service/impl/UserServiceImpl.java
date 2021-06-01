package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.dao.impl.SQLUserDAO;
import by.tsarenkov.shop.service.UserService;
import by.tsarenkov.shop.service.UserInfoValidator;

import java.util.Map;

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
        UserInfoValidator validator = new UserInfoValidator(user);
        Map<String, String> validation = validator.validate();
        if (validation == null | validation.size() == 0) {
            //EmailService.sendRegistrationMessage(user.getEmail());
            try {
                userDAO.registration(user);
            } catch (DAOException e) {
                System.out.println(e);
            }

            return true;
        } else {
            return false;
        }

    }


}

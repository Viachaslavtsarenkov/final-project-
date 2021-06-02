package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.dao.impl.SQLUserDAO;
import by.tsarenkov.shop.service.EmailService;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.UserService;
import by.tsarenkov.shop.service.validator.UserInfoValidator;

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
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        UserInfoValidator validator = new UserInfoValidator(user);
        Map<String, String> validation = validator.validate();
        if (validation == null | validation.size() == 0 && !userDAO.findUser(user.getEmail())) {
            EmailService.sendRegistrationMessage(user.getEmail());
            try {
                userDAO.registration(user);
            } catch (DAOException e) {
                // throw new ServiceException();
            }
            return true;
        } else {
            //
            return false;
        }

    }


}

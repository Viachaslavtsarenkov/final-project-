package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.service.EmailService;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.UserService;
import by.tsarenkov.shop.service.validator.UserInfoValidator;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private final static DAOProvider provider = DAOProvider.getInstance();
    private final static UserDAO userDAO = provider.getUserDAO();

    public UserServiceImpl() {

    }

    @Override
    public User authorization(String login, String password) throws ServiceException{
        User user = null;
        try {
            user =  userDAO.authorization(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

        return user;
    }

    @Override
    public Map<String, String> registration(UserRegistrationInfo user) throws ServiceException {
        UserInfoValidator validator = new UserInfoValidator(user);
        Map<String, String> validation = validator.validate() ;

        // add checking user
        if (validation == null | validation.size() == 0 ) {
            try {
                userDAO.registration(user);
            } catch (DAOException e) {
                 throw new ServiceException();
            }
            EmailService.sendRegistrationMessage(user.getEmail());
            return validation;
        } else {
            System.out.println(validation.size());
            return validation;
        }

    }

    @Override
    public boolean activateAccount(String login, String code) throws ServiceException {
        try {
            userDAO.activateAccount(login, code);
        } catch (DAOException e) {
            throw new ServiceException();
        }

        return true;
    }


}

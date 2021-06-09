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
            // to do msg
            throw new ServiceException();
        }

        return user;
    }

    @Override
    public boolean registration(UserRegistrationInfo user) {
        UserInfoValidator validator = new UserInfoValidator(user);
        Map<String, String> validation = validator.validate();

        // add checking user
        if (validation == null | validation.size() == 0 ) {
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

package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.bean.status.UserStatus;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.service.EmailService;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.UserService;
import by.tsarenkov.shop.service.validator.UserInfoValidator;
import java.util.Map;
import java.util.Random;

public class UserServiceImpl implements UserService {

    private final static DAOProvider PROVIDER = DAOProvider.getInstance();
    private final UserDAO USER_DAO = PROVIDER.getUserDAO();

    public UserServiceImpl() {}

    @Override
    public User authorization(String login, String password) throws ServiceException{
        User user = null;
        try {
            user =  USER_DAO.authorization(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        if (user.getStatus() == UserStatus.NO_ACTIVATED) {
            throw new ServiceException("User is not activated");
        }
        if (user.getStatus() == UserStatus.BLOCKED) {
            throw new ServiceException("User is blocked");
        }
        return user;
    }

    @Override
    public Map<String, String> registration(UserRegistrationInfo user) throws ServiceException {
        String code = null;
        UserInfoValidator validator = new UserInfoValidator(user);
        Map<String, String> validation = validator.validate() ;

        if (validation == null || validation.size() == 0 ) {
            try {
                code = generateCode();
                if (USER_DAO.findUser(user.getEmail())) {
                    validation.put("email", "Email is already in use");
                } else {
                    USER_DAO.registration(user, code);
                    EmailService.sendRegistrationMessage(user.getEmail(), code);
                }
            } catch (DAOException e) {
                 throw new ServiceException(e);
            }
        }
        return validation;

    }

    @Override
    public boolean activateAccount(String login, String code) throws ServiceException {
        try {
            USER_DAO.activateAccount(login, code);
        } catch (DAOException e) {
            throw new ServiceException();
        }

        return true;
    }

    private static String generateCode() {
        String codeSymbols= "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rnd = new Random();
        final StringBuilder code = new StringBuilder();
        for (int i = 0; i < 10; i++) {
           code.append(codeSymbols.charAt(rnd.nextInt(codeSymbols.length())));
        }
        return code.toString();
    }
}

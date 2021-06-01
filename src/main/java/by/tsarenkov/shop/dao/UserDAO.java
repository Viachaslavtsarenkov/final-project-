package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;

public interface UserDAO {

    User authorization(String login, String password) throws DAOException;
    boolean registration(UserRegistrationInfo user) throws DAOException;
    boolean findUser(String login) throws DAOException;
}

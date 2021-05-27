package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.User;

public interface UserDAO {
    User authorization(String login, String password) throws DAOException;
    boolean registration() throws DAOException;
    String check();
}

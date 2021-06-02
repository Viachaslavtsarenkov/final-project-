package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.dao.impl.SQLUserDAO;
import by.tsarenkov.shop.service.UserService;

public class DAOProvider {

    private static final DAOProvider instance = new DAOProvider();

    private final UserDAO userDAO = new SQLUserDAO();

    private DAOProvider() {}

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}

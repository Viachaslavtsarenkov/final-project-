package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.dao.impl.SQLUserDAO;

public class DAOProvider {

    private final UserDAO userDAO = new SQLUserDAO();

    private DAOProvider() {}

    public DAOProvider getInstance() {
        return DAOProviderHelper.instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    private static class DAOProviderHelper {
        private static final DAOProvider instance = new DAOProvider();
    }

}

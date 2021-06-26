package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.dao.impl.SQLOrderDAO;
import by.tsarenkov.shop.dao.impl.SQLProductDAO;
import by.tsarenkov.shop.dao.impl.SQLUserDAO;

public class DAOProvider {

    private static final DAOProvider instance = new DAOProvider();


    private final UserDAO userDAO = new SQLUserDAO();
    private final ProductDAO productDAO = new SQLProductDAO();
    private final OrderDAO orderDAO = new SQLOrderDAO();

    private DAOProvider() {}

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }
}

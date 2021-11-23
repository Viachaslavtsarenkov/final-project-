package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.dao.BasketDAO;
import by.tsarenkov.shop.dao.CreatorDAO;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.SQLQueryStorage;
import by.tsarenkov.shop.dao.db.ConnectionPool;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLBasketDAO extends CreatorDAO implements BasketDAO {

    private static final Logger LOGGER = Logger.getLogger(SQLBasketDAO.class);
    private static final ConnectionPool POOL = ConnectionPool.getInstance();

    private static final String NAME = "name";
    private static final String[] GOODS_ID = new String[]{"good_id"};
    private static final String BRAND = "brand";
    private static final String CHARACTERISTIC = "characteristic";
    private static final String  GOOD_ID = "goods_id";
    private static final String VALUE = "value";
    private static final String COUNT = "count";
    private static final String PRICE = "price";
    private static final String STATUS = "status";
    private static final String PATH = "path";
    private static final String GOOD_ID_COLUMN = "goods_goods_id";
    private static final String NAME_CATEGORY = "name_category";

    public SQLBasketDAO() {}

    public List<Product> getAllProductsFromBasket(int idUser)
            throws DAOException {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_BASKET_PRODUCT);
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            products = constructAllProductByResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Cannot get products from the basket" + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement);
        }
        return products;
    }

    @Override
    public boolean deleteProduct(int idProduct, int idUser)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.DELETE_PRODUCT_QUERY );
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setInt(2, idUser);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Cannot delete the product from the basket: " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement);
        }
        return true;
    }

    @Override
    public boolean addProduct(int idProduct, int idUser)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.ADD_PRODUCT_QUERY);
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setInt(2, idUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Cannot add the product into the basket: " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement);
        }
        return true;
    }

    @Override
    public boolean checkProduct(int idProduct, int idUser) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.CHECK_PRODUCT_QUERY);
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setInt(2, idUser);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt(1) != 0;
        } catch (SQLException e) {
            LOGGER.error("Exception was thrown: " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int getCountProduct(int idUser) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.COUNT_BASKET_PRODUCTS);
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt(COUNT);

        } catch (SQLException e) {
            LOGGER.error("Cannot get count of the products" + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection);
        }
    }

    @Override
    public boolean clearBasket(int idUser) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.DELETE_ALL_PRODUCT_FROM_BASKET);
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Products from the basket wasn't deleted " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement);
        }
        return true;
    }
}

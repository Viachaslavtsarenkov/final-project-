package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.status.ProductStatus;
import by.tsarenkov.shop.dao.CreatorDAO;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.dao.SQLQueryStorage;
import by.tsarenkov.shop.dao.db.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class SQLProductDAO extends CreatorDAO implements ProductDAO {

    private static final Logger LOGGER = Logger.getLogger(SQLProductDAO.class);
    private static final ConnectionPool POOL = ConnectionPool.getInstance();

    private static final String[] GOODS_ID = new String[]{"good_id"};
    private static final String COUNT = "count";


    @Override
    public boolean addNewProduct(int idCategory, Product product, String path,
                                 Map<String, String> productCharacteristic) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.SAVE_PRODUCT_QUERY, GOODS_ID);
            preparedStatement.setString(1, product.getBrand());
            preparedStatement.setInt(2, idCategory);
            preparedStatement.setInt(3, product.getCount());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setString(5, product.getStatus().toString());
            preparedStatement.setString(6, path);
            preparedStatement.setString(7, product.getModel());

            int result = preparedStatement.executeUpdate();
            int goodId = 0;

            if (result == 1) {
               ResultSet rs = preparedStatement.getGeneratedKeys();
               if (rs.next()) {
                   goodId = rs.getInt(1);
               }
               for (Map.Entry<String,String> entry : productCharacteristic.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    preparedStatement = connection.prepareStatement(SQLQueryStorage.SAVE_CHARACTERISTIC_QUERY);
                    preparedStatement.setInt(1, goodId);
                    preparedStatement.setString(2, key);
                    preparedStatement.setString(3, value);
                    preparedStatement.executeUpdate();
               }
            }

        } catch (SQLException e) {
            LOGGER.error("Exception was thrown: " + e);
            throw new DAOException(e);
        } finally {
            POOL.closeConnection(connection, preparedStatement);
        }
        return true;
    }

    @Override
    public boolean changeProduct(Product product,
                                 Map<String, String> productCharacteristic)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.UPDATE_PRODUCT_QUERY);
            preparedStatement.setString(1, product.getBrand());
            preparedStatement.setInt(2, product.getCount());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, product.getStatus().toString());
            preparedStatement.setString(5, product.getPath());
            preparedStatement.setString(6, product.getModel());
            preparedStatement.setInt(7, product.getId());
            preparedStatement.executeUpdate();
            for (Map.Entry<String,String> entry : productCharacteristic.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                preparedStatement = connection.prepareStatement(SQLQueryStorage.UPDATE_CHARACTERISTICS_QUERY);
                preparedStatement.setString(1, value);
                preparedStatement.setInt(2, product.getId());
                preparedStatement.setString(3, key);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error("Exception was thrown: " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement);
        }
        return true;
    }


    @Override
    public List<Product> getAllProducts(ProductName type, int start, int end)
            throws DAOException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        List<Product> products;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_ALL_PRODUCT_QUERY);
            preparedStatement.setString(1, type.toString());
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, end);
            resultSet = preparedStatement.executeQuery();
            products = constructAllProductByResultSet(resultSet);
        } catch (SQLException e) {

            LOGGER.error("Cannot get all products " + e);
            throw new DAOException(e);

        } finally {
            POOL.returnConnectionToPool(connection);
            if (preparedStatement != null) {
                POOL.returnConnectionToPool(connection, preparedStatement);
            }
        }
        return products;
    }

    @Override
    public int getCountAllProducts(ProductName name) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.COUNT_ALL_PRODUCTS);
            preparedStatement.setString(1, name.toString());
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt(COUNT);

        } catch (SQLException e) {
            LOGGER.error("Cannot get count of all products " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection);
        }
    }

    @Override
    public List<Product> getAllProductsByName(String name, int start, int end) throws DAOException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        List<Product> products;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_ALL_PRODUCTS_BY_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, end);
            resultSet = preparedStatement.executeQuery();
            products = constructAllProductByResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Cannot get the list of the products by name " + e);
            throw new DAOException(e);

        } finally {
            POOL.returnConnectionToPool(connection);
            if (preparedStatement != null) {
                POOL.returnConnectionToPool(connection, preparedStatement);
            }
        }
        return products;
    }

    @Override
    public int getCountAllProductsByName(String name) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_COUNT_ALL_PRODUCTS_BY_NAME);
            preparedStatement.setString(1, name.toString());
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt(COUNT);
        } catch (SQLException e) {
            LOGGER.error("Cannot get count of the list of the products by name " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection);
        }
    }

    @Override
    public Product getProduct(int idProduct)
            throws DAOException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Product product = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_PRODUCT_QUERY_BY_ID);
            preparedStatement.setInt(1, idProduct);
            resultSet = preparedStatement.executeQuery();
            product = constructProductByResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Cannot get product by id " + e);
            throw new DAOException(e);
        } finally {
            if (resultSet != null) {
                POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
            }
            POOL.returnConnectionToPool(connection);
        }
        return product;
    }
}

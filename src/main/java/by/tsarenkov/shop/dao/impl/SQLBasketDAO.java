package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.dao.BasketDAO;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.db.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SQLBasketDAO implements BasketDAO {

    private static final Logger log = Logger.getLogger(SQLBasketDAO.class);
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String goodIdColumn = "goods_goods_id";
    private static final String userIdColumn = "user_id_user";
    private static final String countColumn = "count";
    private static final String addProductQuery = "INSERT INTO BASKET (goods_goods_id, user_id_user, count)\n" +
            "VALUES(?, ?, ?)";
    private static final String removeProductQuery = "DELETE FROM BASKET WHERE goods_goods_id = ?, user_id_user = ?";
    private static final String getProductQuery = "";

    @Override
    public List<Product> getAllProducts(int idUser)  throws DAOException {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(getProductQuery);
            // to do

        } catch (SQLException e) {
            throw new DAOException();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.debug("Prepared statement wasn't closed");
            }
            try {
                connection.close();
            } catch (SQLException e) {
                log.debug("Connection wasn't closed");
            }
        }
        return null;
    }

    @Override
    public boolean deleteProduct(int idProduct, int idUser)  throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(removeProductQuery);
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setInt(2, idUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.debug("Prepared statement wasn't closed");
            }
            try {
                connection.close();
            } catch (SQLException e) {
                log.debug("Connection wasn't closed");
            }
        }
        return true;
    }

    @Override
    public boolean addProduct(int idProduct, int idUser, int count) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(addProductQuery);
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setInt(2, idUser);
            preparedStatement.setInt(3, count);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(); // msg
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.debug("Prepared statement wasn't closed");
            }
            try {
                connection.close();
            } catch (SQLException e) {
                log.debug("Connection wasn't closed");
            }
        }
        return true;
    }
}

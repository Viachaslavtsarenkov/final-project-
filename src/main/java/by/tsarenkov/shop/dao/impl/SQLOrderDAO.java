package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.Order;
import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.status.StatusOrder;
import by.tsarenkov.shop.dao.*;
import by.tsarenkov.shop.dao.db.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLOrderDAO extends CreatorDAO implements OrderDAO {

    Logger LOGGER = Logger.getLogger(SQLOrderDAO.class);

    private static final ConnectionPool POOL = ConnectionPool.getInstance();
    private static final String[] ID_ORDER = new String[]{"id_order"};
    private static final String COUNT = "count";

    public SQLOrderDAO() {}

    @Override
    public boolean addNewOrder(Order order) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.ADD_ORDER_QUERY, ID_ORDER);
            preparedStatement.setInt(1, order.getUser().getUserId());
            preparedStatement.setString(2, order.getAddress());
            preparedStatement.setString(3, order.getDeliveryOption());
            preparedStatement.setDouble(4, order.getAmount());
            preparedStatement.setString(5, order.getStatusOrder().toString());
            preparedStatement.setDate(6, new java.sql.Date(order.getDate().getTime()));
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            long result = 0;
            if (resultSet.next()) {
                result = resultSet.getLong(1);
            }

            preparedStatement = connection.prepareStatement(SQLQueryStorage.ADD_ORDER_PRODUCT);

            for(Product product : order.getProducts()) {
                preparedStatement.setLong(1, result);
                preparedStatement.setInt(2, product.getId());
                preparedStatement.setDouble(3, product.getPrice());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot add a new order " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
        return true;
    }

    @Override
    public List<Order> getAllOrders(int start, int end) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orders = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_ALL_ORDERS);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, end);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            orders = constructAllOrdersByResultSet(resultSet);
        } catch(SQLException e) {
            LOGGER.error("Cannot get all orders " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
        return orders;
    }

    @Override
    public int getCountAllOrders() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.COUNT_ALL_ORDERS);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt(COUNT);

        } catch (SQLException e) {
            LOGGER.error("Cannot get count of all orders" + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Order> getOrdersByName(String name, int start, int end)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orders = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_ORDERS_BY_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, end);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            orders = constructAllOrdersByResultSet(resultSet);
        } catch(SQLException e) {
            LOGGER.error("Cannot get all orders " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
        return orders;
    }

    @Override
    public int getCountOrderByName(String name) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_COUNT_ORDERS_BY_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt(COUNT);
        } catch (SQLException e) {
            LOGGER.error("Cannot get count of all orders" + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Order> getAllUserOrders(int idUser, int start, int end) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orders = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_ALL_USER_ORDERS);
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            orders = constructAllOrdersByResultSet(resultSet);
        } catch(SQLException e) {
            LOGGER.error("Cannot get all user's orders " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
        return orders;
    }

    @Override
    public List<Order> getAllOrdersByStatus(StatusOrder statusOrder, int start, int end) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orders = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_ALL_ORDERS_BY_STATUS);
            preparedStatement.setString(1, statusOrder.toString());
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, end);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            orders = constructAllOrdersByResultSet(resultSet);
        } catch(SQLException e) {
            System.out.println(e);
            LOGGER.error("Cannot get all orders " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
        return orders;
    }

    @Override
    public int getCountAllOrdersByStatus(StatusOrder statusOrder) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_COUNT_ORDERS_BY_STATUS);
            preparedStatement.setString(1, statusOrder.toString());
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt(COUNT);

        } catch (SQLException e) {
            System.out.println(e);
            LOGGER.error("Cannot get count of all orders by status" + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
    }


    @Override
    public boolean changeOrderStatus(int idOrder, StatusOrder status) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.CHANGE_ORDER_STATUS);
            preparedStatement.setString(1, status.toString());
            preparedStatement.setInt(2, idOrder);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Cannot get count of the all orders by status " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement);
        }
        return true;
    }

    @Override
    public Order getOrder(int idOrder) throws DAOException {
        Order order = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_ORDER_BY_ID);
            preparedStatement.setInt(1, idOrder);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            order = constructAllOrdersByResultSet(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.error("Cannot get order by id " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection,preparedStatement,resultSet);
        }
        return order;
    }
}

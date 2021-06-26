package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.Order;
import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.status.StatusOrder;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.OrderDAO;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.dao.db.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLOrderDAO implements OrderDAO {

    Logger LOGGER = Logger.getLogger(SQLOrderDAO.class);

    private static final ConnectionPool POOL = ConnectionPool.getInstance();
    private final  ProductDAO PRODUCT_DAO = new SQLProductDAO();

    private static final String[] ID_ORDER = new String[]{"id_order"};
    private static final String ORDER = "order";
    private static final String COUNT = "count";

    private static final  String ADD_ORDER_QUERY = "INSERT INTO ORDERS " +
            "(user_id_user, address, delivery_option, sum, status) " +
            "VALUES(?,?,?,?,?)";
    private static final  String ADD_PRODUCTS_QUERY = "INSERT INTO goods_order" +
            "(orders_id_order, goods_goods_id)" +
            "VALUES(?,?)";
    private static final String CHANGE_STATUS_QUERY = "UPDATE ORDERS SET status= ? where id_order = ?";

    private static final  String GET_ORDER_QUERY = "SELECT address, delivery_option, sum," +
            " status, user_id_user, goods_goods_id\n" +
            "FROM STORE.ORDERS \n" +
            "INNER JOIN  STORE.goods_order\n" +
            "ON orders_id_order = id_order\n" +
            "WHERE id_order = ?";


    private static final String GET_USER_ORDERS = "SELECT id_order, rownumber\n" +
            "from (SELECT id_order, row_number() over(ORDER BY id_order) AS RowNumber FROM store.orders\n" +
            "INNER JOIN store.user\n" +
            "ON user_id_user = id_user\n" +
            "WHERE user_id_user = ?\n" +
            ") AS k\n" +
            "WHERE k.rownumber between ? and ?";

    private static final String COUNT_USERS_ORDERS = "SELECT count(*) AS COUNT  FROM store.orders\n" +
            "where user_id_user = ?";

    private static final String GET_ALL_ORDER = "select id_order\n" +
            "FROM (SELECT id_order, row_number() over(ORDER BY id_order) AS \n" +
            "FROM store.orders) as k\n" +
            "WHERE k.rownumber between ? AND ?";

    private static final String COUNT_ALL_ORDER = "SELECT count(*) from store.orders";

    public SQLOrderDAO() {}

    @Override
    public boolean addNewOrder(int idUser, String address,
                               String deliveryOption,
                               double amount,
                               List<Product> products) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(ADD_ORDER_QUERY, ID_ORDER);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, deliveryOption);
            preparedStatement.setDouble(4, amount);
            preparedStatement.setString(5, StatusOrder.NEW.toString());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            long result = 0;
            if (resultSet.next()) {
                result = resultSet.getLong(1);
            }

            preparedStatement = connection.prepareStatement(ADD_PRODUCTS_QUERY);

            for(Product product : products) {
                preparedStatement.setLong(1, result);
                preparedStatement.setInt(2, product.getId());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            LOGGER.error("Exception was thrown: " + e);
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
            preparedStatement = connection.prepareStatement(GET_ALL_ORDER);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, end);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                if(resultSet.isFirst()) {
                    orders = new ArrayList<>();
                }
                int idOrder = resultSet.getInt(ID_ORDER[0]);
                orders.add(getOrder(idOrder));
            }
        } catch(SQLException e) {
            LOGGER.error("Exception was thrown: " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
        return orders;
    }

    @Override
    public int  getCountAllOrders() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(COUNT_ALL_ORDER);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            return resultSet.getInt(COUNT);

        } catch (SQLException e) {
            LOGGER.error("Exception was thrown: " + e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<Order> getAllOrders(StatusOrder statusOrder, int start, int end) throws DAOException {

        return null;
    }

    @Override
    public List<Order> getAllUserOrders(int idUser, int start, int end) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orders = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(GET_USER_ORDERS);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, end);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                if(resultSet.isFirst()) {
                    orders = new ArrayList<>();
                }
                int idOrder = resultSet.getInt(ID_ORDER[0]);
                orders.add(getOrder(idOrder));
            }
        } catch(SQLException e) {
            LOGGER.error("Exception was thrown: " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
        return orders;
    }

    public int getCountUsersOrders(int idUser) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(COUNT_USERS_ORDERS);
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            return resultSet.getInt(COUNT);

        } catch (SQLException e) {
            LOGGER.error("Exception was thrown: " + e);
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
            preparedStatement = connection.prepareStatement(CHANGE_STATUS_QUERY);
            preparedStatement.setString(1, status.toString());
            preparedStatement.setInt(2, idOrder);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception was thrown: " + e);
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
            preparedStatement = connection.prepareStatement(GET_ORDER_QUERY);
            preparedStatement.setInt(1, idOrder);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                if(resultSet.isFirst()) {
                    String address = resultSet.getString(1);
                    String delivery = resultSet.getString(2);
                    double amount = resultSet.getDouble(3);
                    StatusOrder statusOrder = StatusOrder.valueOf(resultSet.getString(4));
                    int userId = resultSet.getInt(5);
                    order = new Order(idOrder, userId, address, delivery, amount, statusOrder);
                }
                int id = resultSet.getInt(6);
                Product product = PRODUCT_DAO.getProduct(ProductName.EBOOK, id); // todo for all
                order.addProduct(product);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception was thrown: " + e);
            throw new DAOException(e);
        }
        return order;
    }
}

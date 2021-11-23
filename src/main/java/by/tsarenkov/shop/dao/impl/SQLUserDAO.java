package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.bean.UserRole;
import by.tsarenkov.shop.bean.status.UserStatus;
import by.tsarenkov.shop.dao.CreatorDAO;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.dao.SQLQueryStorage;
import by.tsarenkov.shop.dao.db.ConnectionPool;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.List;

public class SQLUserDAO extends CreatorDAO implements UserDAO {

    private final static ConnectionPool POOL = ConnectionPool.getInstance();
    private static final Logger LOGGER = Logger.getLogger(SQLUserDAO.class);


        public SQLUserDAO(){}

    @Override
    public User getUserById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection =POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_USER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            user = constructUser(resultSet);

        } catch (SQLException e) {
            LOGGER.info("Cannot find user into data source");
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
        return user;
    }

    @Override
    public User authorization(String login, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection =POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.AUTHORIZATION);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                user = constructUser(resultSet);
            } else {
                LOGGER.info("Cannot find user by login and password: ");
                throw new DAOException("error.user.wrongData");
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during getting user: " + e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
        return user;
    }

    @Override
    public boolean registration(UserRegistrationInfo user, String code) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = POOL.takeConnection();
            ps = connection.prepareStatement(SQLQueryStorage.REGISTRATION);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, UserStatus.NO_ACTIVATED.toString());
            ps.setString(5, user.getPassword());
            ps.setString(6, UserRole.CUSTOMER.toString());
            ps.setString(7, user.getPhoneNumber());
            ps.setString(8, code);
            ps.executeUpdate();
            POOL.closeConnection(connection, ps);
        }  catch (SQLException e) {
            LOGGER.error("Something went wrong during insertion user: " + e);
            throw new DAOException("error.user");
        } finally {
            POOL.returnConnectionToPool(connection, ps);
        }
        return true;
    }

    @Override
    public boolean findUser(String login) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = POOL.takeConnection();
            ps = connection.prepareStatement(SQLQueryStorage.GETTING_USER_BY_EMAIL);
            ps.setString(1, login);
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            return resultSet.next();
        } catch (SQLException e) {
            LOGGER.error("Something went wrong during searching user: " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, ps);
        }
    }

    @Override
    public boolean activateAccount(String login, String code) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.ACTIVATION_USER);
            preparedStatement.setString(1, UserStatus.ACTIVATED.toString());
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, code);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Something went wrong during activating user: " + e);
            throw new DAOException(e);
        } finally {
          POOL.returnConnectionToPool(connection, preparedStatement);
        }
        return true;
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> userList = null;
        try {
            connection =POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.GET_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            userList = constructAllUsers(resultSet);
        } catch (SQLException e) {
            LOGGER.info("Cannog get the list of all users");
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
        return userList;
    }

    @Override
    public boolean changeUserStatus(int id, UserStatus status) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SQLQueryStorage.CHANGE_USER_STATUS);
            preparedStatement.setString(1, status.toString());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Cannot change user status: " + e);
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement);
        }
        return true;
    }
}

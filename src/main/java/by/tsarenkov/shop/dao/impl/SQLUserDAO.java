package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.bean.UserRole;
import by.tsarenkov.shop.bean.status.UserStatus;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.dao.db.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class SQLUserDAO implements UserDAO {

    private final static ConnectionPool POOL = ConnectionPool.getInstance();
    private static final Logger LOGGER = Logger.getLogger(SQLUserDAO.class);

    private static final String ID_COLUMN = "id_user";
    private static final String USERNAME = "username";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String ROLE = "user_role";
    private static final String PHONE = "phone";
    private static final String STATUS = "status";

    private static final String newUserQuery = "INSERT INTO store.user (username, surname, email, "
            + "status, password, user_role, phone, code)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String queryGettingUser = "SELECT username FROM user WHERE email = ?";
    private static final String activationQuery = "UPDATE user SET status = ? WHERE email = ? " +
            "AND code = ? AND status = 'NO_ACTIVATED'";
    private static final String authorizationQuery = "SELECT * FROM user WHERE email = ? AND password = ?";


    public SQLUserDAO(){}

    @Override
    public User authorization(String login, String password) throws DAOException {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection =POOL.takeConnection();
            preparedStatement = connection.prepareStatement(authorizationQuery);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setUserId(resultSet.getInt(ID_COLUMN));
            user.setName(resultSet.getString(USERNAME));
            user.setSurname(resultSet.getString(SURNAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setPhoneNumber(resultSet.getString(PHONE));
            user.setRole(UserRole.valueOf(resultSet.getString(ROLE)));
            user.setStatus(UserStatus.valueOf(resultSet.getString(STATUS)));
        } catch (SQLException e) {
            //todo
            LOGGER.info("User isn't found");
            throw new DAOException(e);
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
            ps = connection.prepareStatement(newUserQuery);
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
            //TODO
            LOGGER.info("User wasn't registered");
            throw new DAOException("");
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
            ps = connection.prepareStatement(queryGettingUser);
            ps.setString(1, login);
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            return resultSet.next();
        } catch (SQLException e) {
            LOGGER.error("Exception was thrown: " + e);
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
            preparedStatement = connection.prepareStatement(activationQuery);
            preparedStatement.setString(1, UserStatus.ACTIVATED.toString());
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, code);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception was thrown: " + e);
            throw new DAOException(e);
        } finally {
          POOL.returnConnectionToPool(connection, preparedStatement);
        }
        return true;
    }
}

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

    private static final Logger log = Logger.getLogger(SQLUserDAO.class);

    private static final String idColumn = "id_user";
    private static final String usernameColumn = "username";
    private static final String surnameColumn = "surname";
    private static final String emailColumn = "email";
    private static final String passwordColumn = "password";
    private static final String roleColumn = "user_role";
    private static final String phoneColumn = "phone";

    private static final String newUserQuery = "INSERT INTO store.user (username, surname, email, "
            + "status, password, user_role, phone)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String queryGettingUser = "SELECT username FROM user WHERE email = ?";
    private static final String activationQuery = "UPDATE user SET status = ? WHERE email = ?";
    private static final String authorizationQuery = "SELECT * FROM user WHERE email = ? AND password = ?";

    private final static ConnectionPool pool = ConnectionPool.getInstance();
    public SQLUserDAO(){

    }

    @Override
    public User authorization(String login, String password) throws DAOException {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(authorizationQuery);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setUserId(resultSet.getInt(idColumn));
            user.setName(resultSet.getString(usernameColumn));
            user.setSurname(resultSet.getString(surnameColumn));
            user.setEmail(resultSet.getString(emailColumn));
            user.setPassword(resultSet.getString(passwordColumn));
            user.setPhoneNumber(resultSet.getString(phoneColumn));
            user.setRole(UserRole.valueOf(resultSet.getString(roleColumn)));
        } catch (SQLException e) {
            log.info("User isn't found");
            throw new DAOException("err1");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.error("Prepared statement isn't closed");
            }
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Connection isn't closed");
            }
        }
        return user;
    }

    @Override
    public boolean registration(UserRegistrationInfo user) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = pool.takeConnection();
            ps = connection.prepareStatement(newUserQuery);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, UserStatus.NO_ACTIVATED.toString());
            ps.setString(5, user.getPassword());
            ps.setString(6, UserRole.CUSTOMER.toString());
            ps.setString(7, user.getPhoneNumber());
            ps.executeUpdate();
            pool.closeConnection(connection, ps);
        }  catch (SQLException e) {
            log.info("User wasn't registered");
            throw new DAOException("");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                log.error("Prepared statement isn't closed");
            }
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Connection isn't closed");
            }
        }
        return true;
    }

    @Override
    public boolean findUser(String login) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.takeConnection();
            ps = connection.prepareStatement(queryGettingUser);
            ps.setString(1, login);
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            return resultSet.next();
        } catch (SQLException e) {
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                log.debug("Prepared statement isn't closed");
            }
            try {
                connection.close();
            } catch (SQLException e) {
                log.debug("Connection isn't closed");
            }
        }
        return false;
    }

    @Override
    public boolean activateAccount(String login, String code) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(activationQuery);
            preparedStatement.setString(1, UserStatus.ACTIVATED.toString());
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.debug("Prepared statement isn't closed");
            }
            try {
                connection.close();
            } catch (SQLException e) {
                log.debug("Connection isn't closed");
            }
        }
        return false;
    }
}

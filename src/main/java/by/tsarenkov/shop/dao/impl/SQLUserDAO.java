package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.bean.UserRole;
import by.tsarenkov.shop.bean.UserStatus;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.dao.db.ConnectionPool;
import java.sql.*;

public class SQLUserDAO implements UserDAO {

    private static final String newUserQuery = "INSERT INTO store.users (username, surname, email, "
            + "status, password, user_role, phone)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String queryGettingUser = "SELECT username FROM users WHERE email = ?";
    private static final String activationQuery = "UPDATE users SET status = ? WHERE email = ?";
    private static final String authorizationQuery = "SELECT * FROM users WHERE email = ? AND password = ?";

    private final static ConnectionPool pool = ConnectionPool.getInstance();
    public SQLUserDAO(){

    }

    @Override
    public User authorization(String login, String password) {
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
            user.setUserId(resultSet.getInt("id_user"));
            user.setName(resultSet.getString("username"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setPhoneNumber(resultSet.getString("phone"));
            user.setRole(UserRole.valueOf(resultSet.getString("user_role")));
            return user;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {

            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {

            }
        }
        return null;
    }

    @Override
    public boolean registration(UserRegistrationInfo user) {
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
            // date
            ps.executeUpdate();
            pool.closeConnection(connection, ps);
        }  catch (SQLException e) {
            //
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                // to do
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // to do
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
            // to do
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                // to do
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // to do
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
                connection.close();
            } catch (SQLException e) {
                // to do log
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                // to do log
            }

        }
        return false;
    }
}

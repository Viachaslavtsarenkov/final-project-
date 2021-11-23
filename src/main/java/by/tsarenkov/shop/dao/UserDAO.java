package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.bean.status.UserStatus;

import java.util.List;

/**
 * Interface describes the opportunity to add
 * and edit {@link User} into data source
 * **/
public interface UserDAO {

    /**
     * Gets the {@link User} from data source
     *
     * @param id contains user's for getting user from data source
     * @return {@link User}
     * @throws DAOException if an error occurs while getting data of the user
     * **/
    User getUserById(int id) throws DAOException;

    /**
     * Gets the {@link User} from data source by login and password
     *
     * @param login contains user's login for authorization
     * @param password contains user's password for authorization
     * @return {@link User}
     * @throws DAOException if an error occurs while getting data of the user
     * **/
    User authorization(String login, String password) throws DAOException;

    /**
     * Adds the {@link User} into data source
     *
     * @param user contains user's data
     * @param code contains string code for activating account
     * @return true if the user is added into data source
     * @throws DAOException if an error occurs while adding data of the user
     * **/
    boolean registration(UserRegistrationInfo user, String code) throws DAOException;

    /**
     * Checks the {@link User} into data source
     *
     * @param login contains user's login
     * @return true if the user's login isn't busy
     * @throws DAOException if an error occurs while checking data of the user
     * **/
    boolean findUser(String login) throws DAOException;

    /**
     * Activates {@link User}'s account
     *
     * @param login contains user's data
     * @param code contains user's code for activating account
     * @return true if the user's login isn't busy
     * @throws DAOException if an error occurs while activating user
     * **/
    boolean activateAccount(String login, String code) throws DAOException;

    /**
     * Gets list of the {@link User}'s from data source
     * @return list of the users
     * @throws DAOException if an error occurs while getting users
     * **/
    List<User> getAllUsers() throws DAOException;

    /**
     * Changes {@link User}'s status
     * @return true if status is changed
     * @throws DAOException if an error occurs while changing users
     * **/
    boolean changeUserStatus(int id, UserStatus status) throws DAOException;
}

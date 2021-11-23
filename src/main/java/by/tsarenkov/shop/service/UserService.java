package by.tsarenkov.shop.service;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.bean.status.UserStatus;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getUserById(int id) throws ServiceException;
    User authorization(String login, String password) throws ServiceException;
    Map<String,String> registration(UserRegistrationInfo user) throws ServiceException;
    boolean activateAccount(String login, String code) throws ServiceException;
    List<User> getAllUsers() throws ServiceException;
    boolean changeUserStatus(int id, UserStatus status) throws ServiceException;
}

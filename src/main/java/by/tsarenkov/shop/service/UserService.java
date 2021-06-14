package by.tsarenkov.shop.service;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;

import java.util.Map;

public interface UserService {
    User authorization(String login, String password) throws ServiceException;
    Map<String,String> registration(UserRegistrationInfo user) throws ServiceException;
    boolean activateAccount(String login, String code) throws ServiceException;
}

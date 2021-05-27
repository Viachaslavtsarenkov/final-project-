package by.tsarenkov.shop.service;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.dao.ConnectionPoolException;

public interface UserService {
    User authorization(String login, String password);
    boolean registration(UserRegistrationInfo user);
    String check();
}

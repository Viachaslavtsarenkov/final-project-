package by.tsarenkov.shop.service;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRegistrationInfo;

public interface UserService {
    User authorization(String login, String password);
    boolean registration(UserRegistrationInfo user);
}

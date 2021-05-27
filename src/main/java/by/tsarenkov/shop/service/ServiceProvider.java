package by.tsarenkov.shop.service;

import by.tsarenkov.shop.service.impl.UserServiceImpl;

public class ServiceProvider {

    private final UserService userService = new UserServiceImpl();

    private ServiceProvider() {}

    public UserService getUserService() {
        return userService;
    }

    public static ServiceProvider getInstance() {
        return ServiceProviderHelper.instance;
    }

    private static class ServiceProviderHelper {
        private static final ServiceProvider instance = new ServiceProvider();
    }


}

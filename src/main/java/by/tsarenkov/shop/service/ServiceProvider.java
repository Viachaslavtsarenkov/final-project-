package by.tsarenkov.shop.service;

import by.tsarenkov.shop.service.impl.BasketServiceImpl;
import by.tsarenkov.shop.service.impl.OrderServiceImpl;
import by.tsarenkov.shop.service.impl.ProductServiceImpl;
import by.tsarenkov.shop.service.impl.UserServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private final UserService userService = new UserServiceImpl();
    private final ProductService productService = new ProductServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final BasketService basketService = new BasketServiceImpl();

    private ServiceProvider() {}


    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public BasketService getBasketService() {
        return basketService;
    }
}

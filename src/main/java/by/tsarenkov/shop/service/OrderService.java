package by.tsarenkov.shop.service;

import by.tsarenkov.shop.bean.Order;

import java.util.ArrayList;

public interface OrderService {

    boolean addNewOrder(Order order);
    ArrayList<Order> getAllOrders();
    boolean cancelOrder();
    ArrayList<Order> getUserOrder(int userId);

}

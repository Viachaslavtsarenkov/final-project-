package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.Order;

import java.util.ArrayList;

public interface OrderDAO {
    boolean addNewOrder(Order order);
    ArrayList<Order> getAllOrders();
    boolean cancelOrder();
    ArrayList<Order> getUserOrder(int userId);
}

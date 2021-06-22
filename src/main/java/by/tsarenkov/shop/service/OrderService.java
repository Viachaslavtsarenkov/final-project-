package by.tsarenkov.shop.service;

import by.tsarenkov.shop.bean.Order;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {

    boolean addNewOrder(int id) throws ServiceException;
    List<Order> getAllOrders() throws ServiceException;
    boolean cancelOrder(int idOrder) throws ServiceException;
    List<Order> getUserOrder(int userId) throws ServiceException;

}

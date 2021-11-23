package by.tsarenkov.shop.service;

import by.tsarenkov.shop.bean.Order;
import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.status.StatusOrder;
import by.tsarenkov.shop.dao.DAOException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface OrderService {

    boolean addNewOrder(Order order)
            throws ServiceException;
    List<Order> getAllOrders(int start, int end) throws ServiceException;
    int getCountAllOrders() throws ServiceException;

    List<Order> getAllOrdersByStatus(StatusOrder statusOrder, int start, int end) throws ServiceException;
    int getCountAllOrdersByStatus(StatusOrder statusOrder) throws ServiceException;

    List<Order> getOrdersByName(String name, int start, int end) throws ServiceException;
    int getCountOrdersByName(String name) throws  ServiceException;

    List<Order> getAllUserOrders(int idUser) throws ServiceException;
    boolean changeOrderStatus(int idOrder, StatusOrder status) throws ServiceException;
    Order getUserOrder(int userId) throws ServiceException;
    Order getOrder(int idOrder) throws ServiceException;
}

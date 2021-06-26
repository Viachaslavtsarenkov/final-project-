package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.Order;
import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.status.StatusOrder;
import by.tsarenkov.shop.service.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface OrderDAO {
    boolean addNewOrder(int idUser, String address,
                        String deliveryOption,
                        double amount,
                        List<Product> products) throws DAOException;

    List<Order> getAllOrders(int start, int end) throws DAOException;
    int getCountAllOrders() throws DAOException;

    List<Order> getAllOrders(StatusOrder statusOrder, int start, int end) throws DAOException;

    List<Order> getAllUserOrders(int idUser, int start, int end) throws DAOException;
    int getCountUsersOrders(int idUser) throws DAOException;

    boolean changeOrderStatus(int idOrder, StatusOrder status) throws DAOException;
    Order getOrder(int idOrder) throws DAOException;
}

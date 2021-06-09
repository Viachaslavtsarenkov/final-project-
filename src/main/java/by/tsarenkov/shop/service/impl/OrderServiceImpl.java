package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.Order;
import by.tsarenkov.shop.dao.BasketDAO;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.OrderDAO;
import by.tsarenkov.shop.service.OrderService;

import java.util.ArrayList;

public class OrderServiceImpl implements OrderService {
    private static final DAOProvider provider = DAOProvider.getInstance();
    private static final OrderDAO orderDAO = provider.getOrderDAO();

    @Override
    public boolean addNewOrder(Order order) {
        return false;
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        return null;
    }

    @Override
    public boolean cancelOrder() {
        return false;
    }

    @Override
    public ArrayList<Order> getUserOrder(int userId) {
        return null;
    }
}

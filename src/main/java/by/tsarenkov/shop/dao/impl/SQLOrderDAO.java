package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.Order;
import by.tsarenkov.shop.dao.OrderDAO;
import by.tsarenkov.shop.dao.db.ConnectionPool;

import java.util.ArrayList;

public class SQLOrderDAO implements OrderDAO {
    private final static ConnectionPool pool = ConnectionPool.getInstance();

    public SQLOrderDAO() {}

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

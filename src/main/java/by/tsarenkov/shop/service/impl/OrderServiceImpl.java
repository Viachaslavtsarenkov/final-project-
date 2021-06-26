package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.Order;
import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.status.StatusOrder;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.OrderDAO;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.service.OrderService;
import by.tsarenkov.shop.service.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private static final DAOProvider PROVIDER = DAOProvider.getInstance();
    private final OrderDAO ORDER_DAO = PROVIDER.getOrderDAO();

    @Override
    public boolean addNewOrder(int idUser, String address,
                               String deliveryOption,
                               double amount,
                               List<Product> products) throws ServiceException {
        try {
            ORDER_DAO.addNewOrder(idUser, address, deliveryOption, amount, products);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public List<Order> getAllOrders() throws ServiceException {
        return null;
    }

    @Override
    public List<Order> getAllUserOrders(int idUser) throws ServiceException {
        return null;
    }

    @Override
    public boolean changeOrderStatus(int idOrder, StatusOrder status) throws ServiceException {
        return false;
    }

    @Override
    public Order getUserOrder(int userId) throws ServiceException {
        return null;
    }

    @Override
    public Order getOrder(int idOrder) throws ServiceException {
        try {
            return ORDER_DAO.getOrder(idOrder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}


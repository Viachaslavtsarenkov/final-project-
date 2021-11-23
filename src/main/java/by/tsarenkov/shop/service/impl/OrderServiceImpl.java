package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.Order;
import by.tsarenkov.shop.bean.status.StatusOrder;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.OrderDAO;
import by.tsarenkov.shop.service.OrderService;
import by.tsarenkov.shop.service.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private static final DAOProvider PROVIDER = DAOProvider.getInstance();
    private final OrderDAO ORDER_DAO = PROVIDER.getOrderDAO();

    @Override
    public boolean addNewOrder(Order order)
            throws ServiceException {
        try {
            ORDER_DAO.addNewOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public List<Order> getAllOrders(int start, int end)
            throws ServiceException {
        try {
            return ORDER_DAO.getAllOrders(start, end);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getCountAllOrders()
            throws ServiceException{
        try {
            return ORDER_DAO.getCountAllOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAllOrdersByStatus(StatusOrder statusOrder, int start, int end)
            throws ServiceException {
        try {
            return ORDER_DAO.getAllOrdersByStatus(statusOrder, start, end);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getCountAllOrdersByStatus(StatusOrder statusOrder)
            throws ServiceException {
        try {
            return ORDER_DAO.getCountAllOrdersByStatus(statusOrder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public List<Order> getOrdersByName(String name, int start, int end) throws ServiceException {
        try {
            return ORDER_DAO.getOrdersByName(name, start, end);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getCountOrdersByName(String name) throws ServiceException {
        try {
            return ORDER_DAO.getCountOrderByName(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAllUserOrders(int idUser)
            throws ServiceException {
        try {
            return ORDER_DAO.getAllUserOrders(idUser, 1, 1);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeOrderStatus(int idOrder, StatusOrder status)
            throws ServiceException {
        try {
            return ORDER_DAO.changeOrderStatus(idOrder, status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order getUserOrder(int userId)
            throws ServiceException {
        return null;
    }

    @Override
    public Order getOrder(int idOrder)
            throws ServiceException {
        try {
            return ORDER_DAO.getOrder(idOrder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}


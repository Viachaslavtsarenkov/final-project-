package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.dao.BasketDAO;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.service.BasketService;
import by.tsarenkov.shop.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class BasketServiceImpl implements BasketService {
    DAOProvider provider = DAOProvider.getInstance();
    BasketDAO basketDAO = provider.getBasketDAO();
    @Override
    public List<ProductDAO> getAllProducts(int idUser) {
        return null;
    }

    @Override
    public boolean deleteProduct(int idProduct, int idUser) throws ServiceException {
        try {
            basketDAO.deleteProduct(idProduct, idUser);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean addProduct(int idProduct, int idUser, int count) throws ServiceException {
        try {
            basketDAO.addProduct(idProduct, idUser, count);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return true;
    }
}

package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.dao.BasketDAO;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.service.BasketService;

import java.util.ArrayList;

public class BasketServiceImpl implements BasketService {
    DAOProvider provider = DAOProvider.getInstance();
    BasketDAO basketDAO = provider.getBasketDAO();
    @Override
    public ArrayList<ProductDAO> getAllProducts() {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public boolean addProduct(int id) {
        return false;
    }
}

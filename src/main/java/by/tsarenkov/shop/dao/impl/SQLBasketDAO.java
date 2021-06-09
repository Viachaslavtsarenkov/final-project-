package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.dao.BasketDAO;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.dao.db.ConnectionPool;

import java.util.ArrayList;

public class SQLBasketDAO implements BasketDAO {

    private final static ConnectionPool pool = ConnectionPool.getInstance();

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

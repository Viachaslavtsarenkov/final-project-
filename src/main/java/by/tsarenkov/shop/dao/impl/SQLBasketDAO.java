package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.dao.BasketDAO;
import by.tsarenkov.shop.dao.ProductDAO;

import java.util.ArrayList;

public class SQLBasketDAO implements BasketDAO {
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

package by.tsarenkov.shop.service;

import by.tsarenkov.shop.dao.ProductDAO;

import java.util.ArrayList;

public interface BasketService {
    ArrayList<ProductDAO> getAllProducts();
    boolean deleteProduct(int id);
    boolean addProduct(int id);
}

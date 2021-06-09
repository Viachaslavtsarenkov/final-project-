package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.Product;

import java.util.ArrayList;

public interface BasketDAO {
    ArrayList<Product> getAllProducts();
    boolean deleteProduct(int id);
    boolean addProduct(int id);
}

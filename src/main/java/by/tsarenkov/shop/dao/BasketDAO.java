package by.tsarenkov.shop.dao;

import java.util.ArrayList;

public interface BasketDAO {
    ArrayList<ProductDAO> getAllProducts();
    boolean deleteProduct(int id);
    boolean addProduct(int id);
}

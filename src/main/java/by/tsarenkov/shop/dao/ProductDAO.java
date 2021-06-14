package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.good.EBook;

import java.util.List;
import java.util.Map;

public interface ProductDAO {

    boolean addNewProduct(Map<String, String> product) throws DAOException;
    boolean changeProduct() throws DAOException;
    List<Product> getAllProducts() throws DAOException;
    List<Product> getProductByCharacteristics() throws DAOException;

}

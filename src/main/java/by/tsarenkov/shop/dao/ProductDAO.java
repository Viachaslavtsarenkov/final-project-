package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.good.EBook;
import by.tsarenkov.shop.bean.status.ProductStatus;
import by.tsarenkov.shop.service.ServiceException;

import java.util.List;
import java.util.Map;

public interface ProductDAO {

    boolean addNewProduct(int idCategory, String brand,
                          int count, double price,
                          ProductStatus status, String path,
                          Map<String, String> productCharacteristic)
            throws DAOException;

    boolean changeProduct(int idProduct, String brand,
                          int count, double price,
                          ProductStatus status, String path,
                          Map<String, String> productCharacteristic)
            throws DAOException;
    List<Product> getAllProducts(ProductName name,  int start, int end) throws DAOException;
    int getCountAllProducts(ProductName name) throws DAOException;

    List<Product> getProductByCharacteristics() throws DAOException;
    Product getProduct(ProductName name, int idProduct) throws DAOException;
    List<Product> getAllProductsFromBasket(int idUser) throws DAOException;
    boolean deleteProduct(int idProduct, int idUser) throws DAOException;
    boolean addProduct(int idProduct, int idUser, int count) throws DAOException;
    boolean checkProduct(int idProduct, int idUser) throws DAOException;
}

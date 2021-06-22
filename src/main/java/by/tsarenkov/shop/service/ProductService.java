package by.tsarenkov.shop.service;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.status.ProductStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProductService {
     List<Product> getAllProducts(ProductName name) throws ServiceException;
     List<Product> getProductsByCharacteristics(ProductName productName) throws ServiceException;
     boolean addNewProduct(int idCategory, String brand,
                           int count, double price,
                           ProductStatus status, String path,
                           Map<String, String> productCharacteristic)
             throws ServiceException;
     Product getProduct(ProductName name, int idProduct) throws ServiceException;

     boolean changeProduct(int idProduct, String brand,
                           int count, double price,
                           ProductStatus status, String path,
                           Map<String, String> productCharacteristic)
             throws ServiceException;

     List<Product> getAllProductsFromBasket(int idUser) throws ServiceException;
     List<Product> getAllProductsFromCookies() throws ServiceException;

     boolean addProduct(int idProduct, int idUser, int count) throws ServiceException;
     boolean addProductToCookies(int idProduct, int idUser) throws ServiceException;
     boolean checkProduct(int idProduct, int idUser) throws ServiceException;
     boolean checkProductInCookies(int idProduct) throws ServiceException;
     boolean deleteProduct(int idProduct, int idUser) throws ServiceException;
     boolean deleteProductFromCookies(int idProduct) throws ServiceException;
}

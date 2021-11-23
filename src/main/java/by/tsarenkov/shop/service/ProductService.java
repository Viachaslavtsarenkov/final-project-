package by.tsarenkov.shop.service;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.status.ProductStatus;

import javax.servlet.http.Cookie;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProductService {

     List<Product> getAllProducts(ProductName name, int start, int end) throws ServiceException;
     int getCountAllProducts(ProductName name) throws ServiceException;
     List<Product> getAllProductsByName(String name, int start, int end) throws ServiceException;
     int getCountAllProductsByName(String name) throws ServiceException;

     List<Product> getProductsByCharacteristics(ProductName productName) throws ServiceException;
     boolean addNewProduct(int idCategory, Product product, InputStream file, String path,
                           Map<String, String> productCharacteristic)
             throws ServiceException;
     Product getProduct(int idProduct) throws ServiceException;
     boolean changeProduct(ProductName name, Product product,
                           InputStream file, String path,
                           Map<String, String> productCharacteristic)
             throws ServiceException;

}

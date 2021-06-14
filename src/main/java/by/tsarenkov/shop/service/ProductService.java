package by.tsarenkov.shop.service;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProductService {
     List<Product> getAllProducts() throws ServiceException;
     List<Product> getProductsByCharacteristics(ProductName productName) throws ServiceException;
     boolean addNewProduct(Map<String, String> productCharacteristic) throws ServiceException;
}

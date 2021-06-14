package by.tsarenkov.shop.service;

import by.tsarenkov.shop.dao.ProductDAO;

import java.util.ArrayList;
import java.util.List;

public interface BasketService {
    List<ProductDAO> getAllProducts(int idUser) throws ServiceException;
    boolean deleteProduct(int idProduct, int idUser) throws ServiceException;
    boolean addProduct(int idProduct, int idUser, int count) throws ServiceException;
   // boolean checkProductInBasket(int idProduct, int idUser) throws ServiceException;
}

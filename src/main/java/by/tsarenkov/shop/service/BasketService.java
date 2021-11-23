package by.tsarenkov.shop.service;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.dao.DAOException;

import javax.servlet.http.Cookie;
import java.util.List;

public interface BasketService {
    List<Product> getAllProductsFromBasket(int idUser) throws ServiceException;
    List<Product> getAllProductsFromCookies(Cookie[] cookies) throws ServiceException;

    boolean addProduct(int idProduct, int idUser) throws ServiceException;
    boolean checkProduct(int idProduct, int idUser) throws ServiceException;
    boolean checkProductInCookies(Cookie[] cookie, int id) throws ServiceException;
    boolean deleteProduct(int idProduct, int idUser) throws ServiceException;
    int getCountProduct(int idUser) throws ServiceException;
    int getCountProductsFromCookies(Cookie[] cookies) throws ServiceException;
    Cookie deleteProductFromCoolies(Cookie[] cookies, int idProduct) throws ServiceException;
    Cookie[] transferProductsFromCookie(Cookie[] cookies, int idUser) throws ServiceException;
    boolean clearBasket(int idUser) throws ServiceException;
}

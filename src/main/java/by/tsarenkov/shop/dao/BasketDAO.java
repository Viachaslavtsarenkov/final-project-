package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.Product;

import java.util.ArrayList;
import java.util.List;

public interface BasketDAO {
    List<Product> getAllProducts(int idUser) throws DAOException;
    boolean deleteProduct(int idProduct, int idUser) throws DAOException;
    boolean addProduct(int idProduct, int idUser, int count) throws DAOException;
}

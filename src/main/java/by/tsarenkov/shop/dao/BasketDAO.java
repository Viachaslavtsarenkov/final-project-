package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.Product;

import java.util.List;

/**
 * Interface describes the opportunity to add
 * and edit {@link Product} into basket
 * **/

public interface BasketDAO {

    /**
     * Gets the {@link Product} from data source
     *
     * @param idUser contains user's for getting user's products from basket
     * @return list of the products
     * @throws DAOException if an error occurs while getting data of the products
     * **/
    List<Product> getAllProductsFromBasket(int idUser) throws DAOException;

    /**
     * Deletes the {@link Product} from data source
     *
     * @param idUser contains user's id for deleting product from user's basket
     * @param idProduct  contains id of the products for deleting
     * @return true if the product is deleted
     * @throws DAOException if an error occurs while deleting data of the products
     * **/
    boolean deleteProduct(int idProduct, int idUser) throws DAOException;

    /**
     * Adds the {@link Product} into data source
     *
     * @param idUser contains user's id for adding product from user's basket
     * @param idProduct  contains id of the products for adding
     * @return true if the product is added
     * @throws DAOException if an error occurs while adding data of the products
     * **/
    boolean addProduct(int idProduct, int idUser) throws DAOException;

    /**
     * Checks the  {@link Product} into basket
     *
     * @param idUser contains user's id for checking product from user's basket
     * @param idProduct  contains id of the products for checking
     * @return true if the product is added
     * @throws DAOException if an error occurs while checking product
     * **/
    boolean checkProduct(int idProduct, int idUser) throws DAOException;

    /**
     * Gets count of the {@link Product} into user's basket
     *
     * @param idUser contains user's id for checking product from user's basket
     * @return count of products into user's basket
     * @throws DAOException if an error occurs while getting count of products
     * **/
    int getCountProduct(int idUser) throws DAOException;

    /**
     * Clears products the {@link Product} from user's basket
     *
     * @param idUser contains user's id for deleting user's products from basket
     * @return true if products are deleted from user's basket
     * @throws DAOException if an error occurs while deleting count of products
     * **/
    boolean clearBasket(int idUser) throws DAOException;
}

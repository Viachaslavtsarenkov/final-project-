package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import java.util.List;
import java.util.Map;

/**
 * Interface describes the opportunity to add
 * and edit {@link Product} into data source
 * **/

public interface ProductDAO {

    /**
     * Adds the {@Product} into data source
     *
     * @param idCategory contains id category of the product
     * @param product contains data of the product
     * @param path contains path to the image
     * @param productCharacteristic contains characteristic of sub classes of the Product
     * @return true if the product was added into data base
     * @throws DAOException if an error occurs while adding data of the product
     * **/
    boolean addNewProduct(int idCategory, Product product, String path,
                          Map<String, String> productCharacteristic)
            throws DAOException;

    /**
     * Changes the {@link Product} into data source
     * @param product contains data of the product
     * @param productCharacteristic contains characteristic of sub classes of the Product
     * @return true if the product was changed
     * @throws DAOException if an error occurs while changing data of the product
     */
    boolean changeProduct(Product product,
                          Map<String, String> productCharacteristic)
            throws DAOException;

     /**
      * Gets all list of the {@link Product}
      *
      * @param name contains the category of the product
      * @param start contains start index for pagination
      * @param end contains end index for pagination
      * @return all list of the products
      * @throws DAOException if an error occurs while getting  list of the products
      * **/
    List<Product> getAllProducts(ProductName name, int start, int end)
            throws DAOException;

    /**
     * Gets all list of the products
     * @param name contains the category of the product
     * @return count of all list of the products that using for pagination
     * @throws DAOException if an error occurs while getting  list of the products
     * **/
    int getCountAllProducts(ProductName name) throws DAOException;

    /**
     * Gets all list of the {@link Product}
     *
     * @param name contains name of the product for searching into data source
     * @param start contains start index for pagination
     * @param end contains end index for pagination
     * @return all list of the products that are found by name
     * @throws DAOException
     * **/
    List<Product> getAllProductsByName(String name, int start, int end) throws DAOException;

    /**
     *Gets count of all ${@link Product}s by name
     *
     * @param name contains the category of the product
     * @return count of all list of the products that are found by name that using for pagination
     * @throws DAOException
     * **/
    int getCountAllProductsByName(String name) throws DAOException;

    /**
     *
     * Gets {@link Product} by id
     *
     * @param idProduct contains the id of the product for searching into data source
     * @return {@link Product} that are found by id
     * @throws DAOException
     * **/
    Product getProduct(int idProduct) throws DAOException;



}

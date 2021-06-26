package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.status.ProductStatus;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.service.PhotoLoader;
import by.tsarenkov.shop.service.ProductService;
import by.tsarenkov.shop.service.ServiceException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    public static final DAOProvider PROVIDER = DAOProvider.getInstance();
    public final ProductDAO PRODUCT_DAO = PROVIDER .getProductDAO();

    @Override
    public List<Product> getAllProducts(ProductName name, int start, int end) throws ServiceException {
        List<Product> products = null;
        try {
            products = PRODUCT_DAO.getAllProducts(name, start, end);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return products;
    }

    @Override
    public int getCountAllProducts(ProductName name) throws ServiceException {
        try {
            return PRODUCT_DAO.getCountAllProducts(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProductsByCharacteristics(ProductName productName) {
        return null;
    }

    @Override
    public boolean addNewProduct(int idCategory, String brand,
                                 int count, double price,
                                 ProductStatus status, InputStream file, String path,
                                 Map<String, String> productCharacteristic) throws ServiceException{

        try {
            String fullPath = PhotoLoader.loadPhoto(file, path);
            PRODUCT_DAO.addNewProduct(idCategory, brand,
                    count, price, status,
                    fullPath, productCharacteristic);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public Product getProduct(ProductName name, int idProduct) throws ServiceException {
        Product product = null;
        try {
            product = PRODUCT_DAO.getProduct(name, idProduct);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return product;
    }

    @Override
    public boolean changeProduct(int idProduct, String brand,
                                 int count, double price, ProductStatus status,
                                 String path, Map<String, String> productCharacteristic)
            throws ServiceException {
        try {
            PRODUCT_DAO.changeProduct(idProduct, brand, count, price, status, path,productCharacteristic);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public List<Product> getAllProductsFromBasket(int idUser) throws ServiceException {
        try {
            return PRODUCT_DAO.getAllProductsFromBasket(idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getAllProductsFromCookies() throws ServiceException {
        return null;
    }

    @Override
    public boolean deleteProduct(int idProduct, int idUser) throws ServiceException {
        try {
            return PRODUCT_DAO.deleteProduct(idProduct, idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteProductFromCookies(int idProduct) throws ServiceException {
        return false;
    }

    @Override
    public boolean addProduct(int idProduct, int idUser, int count) throws ServiceException {
        try {
            return PRODUCT_DAO.addProduct(idProduct, idUser, count);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addProductToCookies(int idProduct, int idUser) throws ServiceException {
        return false;
    }

    @Override
    public boolean checkProduct(int idProduct, int idUser) throws ServiceException {
        try {
            return PRODUCT_DAO.checkProduct(idProduct, idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkProductInCookies(int idProduct) throws ServiceException {
        return false;
    }

}

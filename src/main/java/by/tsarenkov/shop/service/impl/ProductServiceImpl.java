package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.status.ProductStatus;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.service.ProductService;
import by.tsarenkov.shop.service.ServiceException;

import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    public static final DAOProvider provider = DAOProvider.getInstance();
    public static final ProductDAO productDAO = provider.getProductDAO();

    @Override
    public List<Product> getAllProducts(ProductName name) throws ServiceException {
        List<Product> products = null;
        try {
            products = productDAO.getAllProducts(name);
        } catch (DAOException e) {
            throw new ServiceException();
        }
        return products;
    }

    @Override
    public List<Product> getProductsByCharacteristics(ProductName productName) {
        return null;
    }

    @Override
    public boolean addNewProduct(int idCategory, String brand,
                                 int count, double price,
                                 ProductStatus status, String path,
                                 Map<String, String> productCharacteristic) throws ServiceException{
        try {
            productDAO.addNewProduct(idCategory, brand,
                    count, price, status,
                    path, productCharacteristic);

        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return true;
    }

    @Override
    public Product getProduct(ProductName name, int idProduct) throws ServiceException {
        Product product = null;
        try {
            product = productDAO.getProduct(name, idProduct);
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
             productDAO.changeProduct(idProduct, brand, count, price, status, path,productCharacteristic);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public List<Product> getAllProductsFromBasket(int idUser) throws ServiceException {
        try {
            return productDAO.getAllProductsFromBasket(idUser);
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
            return productDAO.deleteProduct(idProduct, idUser);
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
            return productDAO.addProduct(idProduct, idUser, count);
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
            return productDAO.checkProduct(idProduct, idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkProductInCookies(int idProduct) throws ServiceException {
        return false;
    }

}

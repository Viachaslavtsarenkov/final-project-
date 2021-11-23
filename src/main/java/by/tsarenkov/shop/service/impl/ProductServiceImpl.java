package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.util.PhotoLoader;
import by.tsarenkov.shop.service.ProductService;
import by.tsarenkov.shop.service.ServiceException;

import java.io.InputStream;
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
    public List<Product> getAllProductsByName(String name, int start, int end)
            throws ServiceException {
        try {
            return PRODUCT_DAO.getAllProductsByName(name, start, end);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getCountAllProductsByName(String name) throws ServiceException {
        try {
            return PRODUCT_DAO.getCountAllProductsByName(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProductsByCharacteristics(ProductName productName) {
        return null;
    }

    @Override
    public boolean addNewProduct(int idCategory, Product product, InputStream file, String path,
                                 Map<String, String> productCharacteristic)
            throws ServiceException {

        try {
            String fullPath;
            if (!path.equals("default.png")) {
                fullPath = PhotoLoader.loadPhoto(file, path,null);
            } else {
                fullPath = path;
            }
            PRODUCT_DAO.addNewProduct(idCategory, product,
                    fullPath, productCharacteristic);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public Product getProduct(int idProduct) throws ServiceException {
        Product product = null;
        try {
            product = PRODUCT_DAO.getProduct(idProduct);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return product;
    }

    @Override
    public boolean changeProduct(ProductName name, Product product, InputStream file,
                                 String path, Map<String, String> productCharacteristic)
            throws ServiceException {

        try {
            String img = PRODUCT_DAO.getProduct(product.getId()).getPath();
            if((path != null & img.equals("default.png")) || path != null) {
                img = PhotoLoader.loadPhoto(file, path, img);
            } else {
                img = PRODUCT_DAO.getProduct(product.getId()).getPath();
            }
            product.setPath(img);
            PRODUCT_DAO.changeProduct(product,productCharacteristic);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }
}

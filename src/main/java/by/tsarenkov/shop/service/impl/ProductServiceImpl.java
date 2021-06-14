package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.good.EBook;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.service.ProductService;
import by.tsarenkov.shop.service.ServiceException;

import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    public static final DAOProvider provider = DAOProvider.getInstance();
    public static final ProductDAO productDAO = provider.getEBookDAO();

    @Override
    public List<Product> getAllProducts() throws ServiceException {
        List<Product> products = null;
        try {
            products = productDAO.getAllProducts();
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
    public boolean addNewProduct(Map<String, String> product) throws ServiceException{
        try {
            productDAO.addNewProduct(product);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return true;
    }

}

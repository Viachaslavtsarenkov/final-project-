package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.dao.BasketDAO;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.service.BasketService;
import by.tsarenkov.shop.service.ServiceException;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

public class BasketServiceImpl implements BasketService {

    public static final DAOProvider PROVIDER = DAOProvider.getInstance();
    public final BasketDAO BASKET_DAO = PROVIDER .getBasketDAO();
    private static final String PRODUCT_PREFIX = "product-";
    private final ProductDAO PRODUCT_DAO = PROVIDER.getProductDAO();

    @Override
    public List<Product> getAllProductsFromBasket(int idUser)
            throws ServiceException {
        try {
            return BASKET_DAO.getAllProductsFromBasket(idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getAllProductsFromCookies(Cookie[] cookies)
            throws ServiceException {
        List<Product> products = new ArrayList<>();
        try {
            for (int i = 0; i < cookies.length; ++i) {
                String key = cookies[i].getName();
                if (key.contains(PRODUCT_PREFIX)) {
                    int value = Integer.parseInt(cookies[i].getValue());
                    Product product = PRODUCT_DAO.getProduct(value);
                    products.add(product);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return products;
    }

    @Override
    public boolean deleteProduct(int idProduct, int idUser)
            throws ServiceException {
        try {
            return BASKET_DAO.deleteProduct(idProduct, idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getCountProduct(int idUser) throws ServiceException {
        try {
            return BASKET_DAO.getCountProduct(idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getCountProductsFromCookies(Cookie[] cookies) throws ServiceException {
        int count = 0;
        for (int i = 0; i < cookies.length; ++i) {
            String key = cookies[i].getName();
            if (key.contains(PRODUCT_PREFIX)) {
               ++count;
            }
        }
        return count;
    }

    @Override
    public Cookie deleteProductFromCoolies(Cookie[] cookies, int idProduct) throws ServiceException {
        for (int i = 0; i < cookies.length; ++i) {
            String key = cookies[i].getName();
            if (key.equals(PRODUCT_PREFIX + idProduct)) {
                Cookie cookie = cookies[i];
                cookie.setMaxAge(0);
                return cookie;
            }
        }
        return null;
    }

    @Override
    public Cookie[] transferProductsFromCookie(Cookie[] cookies, int idUser) throws ServiceException {
        for (int i = 0; i < cookies.length; ++i) {
            String key = cookies[i].getName();
            if (key.contains(PRODUCT_PREFIX)) {
                int value = Integer.parseInt(cookies[i].getValue());
                if(!checkProduct(value, idUser)) {
                    addProduct(value, idUser);
                }
                Cookie cookie = cookies[i];
                cookie.setMaxAge(0);
            }
        }
        return cookies;
    }

    @Override
    public boolean clearBasket(int idUser) throws ServiceException {
        try {
            BASKET_DAO.clearBasket(idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public boolean addProduct(int idProduct, int idUser)
            throws ServiceException {
        try {
            return BASKET_DAO.addProduct(idProduct, idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkProduct(int idProduct, int idUser)
            throws ServiceException {
        try {
            return BASKET_DAO.checkProduct(idProduct, idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkProductInCookies(Cookie[] cookie, int id) throws ServiceException {
        for (int i = 0; i < cookie.length; ++i) {
            String key = cookie[i].getName();
            if (key.contains(PRODUCT_PREFIX + id)) {
                return true;
            }
        }
        return false;
    }
}

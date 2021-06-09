package by.tsarenkov.shop.service.impl;

import by.tsarenkov.shop.bean.good.EBook;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.service.ProductService;

public class ProductServiceImpl implements ProductService {

    public static final DAOProvider provider = DAOProvider.getInstance();
    public static final ProductDAO productDAO = provider.getProductDAO();

    @Override
    public boolean addNewEBook() {
        EBook eBook = new EBook();
        productDAO.addNewEBook(eBook);
        return false;
    }

}

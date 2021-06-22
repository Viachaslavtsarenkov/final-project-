package by.tsarenkov.shop.controller.impl;
import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.characteristic.EBookCharacteristic;
import by.tsarenkov.shop.bean.characteristic.LaptopCharacteristic;
import by.tsarenkov.shop.bean.characteristic.SmartphoneCharacteristic;
import by.tsarenkov.shop.bean.characteristic.TabletCharacteristic;
import by.tsarenkov.shop.bean.good.EBook;
import by.tsarenkov.shop.bean.good.Tablet;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.ProductService;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductsView implements Command {
    // TODO RENAME
    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final ProductService EBOOK_SERVICE = PROVIDER.getProductService();
    private static final String PRODUCTS_PAGE_PATH = "/WEB-INF/jsp/products_page.jsp";
    private static final String PRODUCT_ATTR = "products";
    private static final String NAME = "name";

    public ProductsView() {}
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher requestDispatcher = null;
        ProductName name = ProductName.valueOf(request
                .getParameter(NAME).toString().toUpperCase());
        try {
            List<? extends Product> products = EBOOK_SERVICE.getAllProducts(name);
            request.setAttribute(NAME, name.toString().toLowerCase());
            request.setAttribute(PRODUCT_ATTR, products);
            requestDispatcher = request.getRequestDispatcher(PRODUCTS_PAGE_PATH);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            //TODO ADD LOG
        }
    }
}

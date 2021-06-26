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
import by.tsarenkov.shop.service.PaginationTag;
import by.tsarenkov.shop.service.ProductService;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductsView implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final ProductService PRODUCT_SERVICE = PROVIDER.getProductService();
    private static final String PRODUCTS_PAGE_PATH = "/WEB-INF/jsp/products_page.jsp";
    private static final String ERROR_PAGE_PATH = "/WEB-INF/jsp/error.jsp";

    private static final String PRODUCT_ATTR = "products";
    private static final String NAME = "name";
    private static final String CURRENT_PAGE = "page";
    private static final String COUNT_PAGE = "countPage";

    public ProductsView() {}
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher requestDispatcher = null;
        ProductName name = ProductName.valueOf(request
                .getParameter(NAME).toUpperCase());

        int page = 1;
        int countElements = 8;
        double countAll = 0;
        if (request.getParameter(CURRENT_PAGE) != null) {
            page = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        }
        try {
            countAll = PRODUCT_SERVICE.getCountAllProducts(name);
            List<Product> products = PRODUCT_SERVICE.getAllProducts(name,
                    (page - 1) * countElements + 1, countElements * page);
            request.setAttribute(CURRENT_PAGE, page);
            request.setAttribute(NAME, name.toString().toLowerCase());
            request.setAttribute(PRODUCT_ATTR, products);
            request.setAttribute(COUNT_PAGE, Math.ceil(countAll / countElements));
            requestDispatcher = request.getRequestDispatcher(PRODUCTS_PAGE_PATH);
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(ERROR_PAGE_PATH);
        }
        requestDispatcher.forward(request, response);
    }
}

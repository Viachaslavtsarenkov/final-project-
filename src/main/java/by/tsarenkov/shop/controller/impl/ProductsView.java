package by.tsarenkov.shop.controller.impl;
import by.tsarenkov.shop.bean.Order;
import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.SearchProductСriterion;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.PageStorage;
import by.tsarenkov.shop.service.ProductService;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.cert.CRLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductsView implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final ProductService PRODUCT_SERVICE = PROVIDER.getProductService();

    private static final String PRODUCT_ATTR = "products";
    private static final String NAME = "name";
    private static final String CURRENT_PAGE = "page";
    private static final String COUNT_PAGE = "countPage";
    private static final String CRITERION = "criterion";
    private static final String SEARCH = "search";
    private static final String LANG_PAGE = "langpage";
    private static final String PAGE = "page";
    private static String COMMAND_PAGE = "productview&";

    private int page = 1;
    private int countElements = 8;
    private int countPages;


    public ProductsView() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher requestDispatcher = null;
        SearchProductСriterion criterion = SearchProductСriterion
                .valueOf(request.getParameter(CRITERION).toUpperCase());
        double countAll = 0;
        ProductName name = null;
        String searchName = null;
        List<Product> currentProducts = null;
        page = Integer.parseInt(request.getParameter(PAGE));
        try {
            switch (criterion) {
                case ALL:
                    name = ProductName.valueOf(request
                            .getParameter(NAME).toUpperCase());
                    request.setAttribute(NAME, name.toString().toLowerCase());
                    COMMAND_PAGE = COMMAND_PAGE + String.format("%s=%s&", NAME, name.toString().toLowerCase());
                    countAll = PRODUCT_SERVICE.getCountAllProducts(name);
                    request.setAttribute(NAME, name.toString().toLowerCase());
                    break;
                case NAME:
                    searchName = request.getParameter(SEARCH) + "%";
                    countAll = PRODUCT_SERVICE.getCountAllProductsByName(searchName);
                break;
                case STATUS:
                    break;
            }
        } catch (ServiceException e) {
            response.sendRedirect(PageStorage.ERROR_PAGE_PATH.getPATH());
        }


        countPages =  (int) Math.ceil(countAll / 8);
        if (page != countPages) {
            countElements = 8;
        } else {
            countElements = (int)countAll % 8;
        }
        int start = (page - 1) * 8 + 1;

        try {
            switch (criterion) {
                case ALL:
                    currentProducts = PRODUCT_SERVICE
                            .getAllProducts(name, start, start + countElements - 1);
                    break;
                case NAME:
                    currentProducts = PRODUCT_SERVICE
                            .getAllProductsByName(searchName, start, start + countElements - 1);
                    break;
                case STATUS:
                    break;
            }
        } catch (ServiceException e) {
            response.sendRedirect(PageStorage.ERROR_PAGE_PATH.getPATH());
        }

        request.setAttribute(PRODUCT_ATTR, currentProducts);
        request.setAttribute(CURRENT_PAGE, page);
        request.setAttribute(COUNT_PAGE,countPages);
        request.setAttribute(CRITERION, criterion);
        request.setAttribute(LANG_PAGE,COMMAND_PAGE + String.format("%s=%s&%s=%s&%s=%s",
                        CURRENT_PAGE, page, COUNT_PAGE, countPages, CRITERION, criterion));
        requestDispatcher = request.getRequestDispatcher(PageStorage.PRODUCTS_PAGE_PATH.getPATH());
        requestDispatcher.forward(request, response);
    }
}

package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
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

public class ChangeProduct implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final ProductService SERVICE = PROVIDER.getProductService();
    private static final String ID_PRODUCT = "id";
    private static final String NAME = "name";
    private static final String PRODUCT = "product";
    private static final String PRODUCT_INPUT_PAGE = "/WEB-INF/jsp/?_input_page.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    public ChangeProduct() {}
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter(ID_PRODUCT));
        ProductName name = ProductName.valueOf(request.getParameter(NAME).toUpperCase());
        RequestDispatcher requestDispatcher = null;
        try {
            Product product = SERVICE.getProduct(name, id);
            request.setAttribute(PRODUCT , product);
            requestDispatcher = request
                    .getRequestDispatcher(PRODUCT_INPUT_PAGE.replace("?", name.toString().toLowerCase()));
        } catch (ServiceException e) {
            requestDispatcher = request
                    .getRequestDispatcher(ERROR_PAGE);
        }
        requestDispatcher.forward(request, response);
    }
}

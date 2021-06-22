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
    private static final ProductService SERVICE = PROVIDER.getProductService();
    private static final String ID_PRODUCT = "id";
    private static final String NAME = "name";


    private static final String PRODUCT_INPUT_PAGE = "/WEB-INF/jsp/?_input_page.jsp";

    public ChangeProduct() {}
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter(ID_PRODUCT));
        ProductName name = ProductName.valueOf(request.getParameter(NAME).toUpperCase());
        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PRODUCT_INPUT_PAGE.replace("?", name.toString().toLowerCase()));
        try {
            Product product = SERVICE.getProduct(name, id);
            switch (name) {
                case EBOOK:
                    request.setAttribute(name.toString().toLowerCase(), (EBook) product);
                    break;
                case TABLET:
                    request.setAttribute(name.toString().toLowerCase(), (Tablet) product);
                    break;
                case LAPTOP:
                    break;
                case SMARTPHONE:
                    break;
            }
        } catch (ServiceException e) {
            //TODO ADD LOG
        }
        requestDispatcher.forward(request, response);
    }
}

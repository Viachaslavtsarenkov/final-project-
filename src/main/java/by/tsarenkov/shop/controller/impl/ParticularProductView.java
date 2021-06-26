package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.UserRole;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class ParticularProductView implements Command {
    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final ProductService SERVICE = PROVIDER.getProductService();

    private static final String PRODUCT_PAGE = "/WEB-INF/jsp/particular_productView.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";
    private static final String ID_PRODUCT = "id";
    private static final String PRODUCT_ATTR = "product";
    private static final String TYPE_ATTR = "type";
    private static final String BASKET_ATTR = "basket";
    private static final String ID_USER_ATTR = "user";
    private static final String ROLE_ATTR = "role";
    private static final String NAME = "name";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter(ID_PRODUCT));
        RequestDispatcher requestDispatcher = null;
        HttpSession session = request.getSession(false);
        ProductName name  = ProductName.valueOf(request.getParameter(NAME).toUpperCase());
        try {
            Product product = SERVICE.getProduct(name, id);
            request.setAttribute(PRODUCT_ATTR, product);
            request.setAttribute(TYPE_ATTR, name.toString());
            if (UserRole.CUSTOMER.toString().equals(session.getAttribute(ROLE_ATTR).toString())) {
                int idUser = Integer.parseInt(session.getAttribute(ID_USER_ATTR).toString());
                request.setAttribute(BASKET_ATTR, SERVICE.checkProduct(id, idUser));
            } else if (!UserRole.ADMIN.toString().equals(session.getAttribute(ROLE_ATTR).toString())) {
                // todo   checking product in cookies
            }
            requestDispatcher = request.getRequestDispatcher(PRODUCT_PAGE);
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
        }
        requestDispatcher.forward(request, response);
    }
}

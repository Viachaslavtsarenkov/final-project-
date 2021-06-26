package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.UserRole;
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
import java.util.List;

public class GoToOrderPage implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final ProductService SERVICE = PROVIDER.getProductService();

    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";
    private static final String ORDER_PAGE_PATH = "/WEB-INF/jsp/order_input.jsp";
    private static final String LOGIN_PAGE_PATH = "/WEB-INF/jsp/login.jsp";
    private static final String ID_USER_ATTR = "user";
    private static final String ROLE_ATTR = "role";
    private static final String ORDER = "order";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        List<Product> products= null;
        RequestDispatcher dispatcher = null;
        try {
            if (UserRole.CUSTOMER.toString().equals(session.getAttribute(ROLE_ATTR).toString())) {
                int idUser = Integer.parseInt(session.getAttribute(ID_USER_ATTR).toString());
                products = SERVICE.getAllProductsFromBasket(idUser);
                dispatcher = request.getRequestDispatcher(ORDER_PAGE_PATH);
                request.setAttribute(ORDER, products);
            } else {
                dispatcher = request.getRequestDispatcher(LOGIN_PAGE_PATH);
            }
        } catch (ServiceException e) {
            dispatcher = request.getRequestDispatcher(ERROR_PAGE);
        }
        dispatcher.forward(request, response);
    }
}
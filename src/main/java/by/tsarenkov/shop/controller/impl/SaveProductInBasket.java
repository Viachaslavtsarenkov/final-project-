package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.UserRole;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveProductInBasket implements Command{
    private static final String ID_PRODUCT_PARAMETER = "id";
    private static final String ID_USER_ATTR = "user";
    private static final String ROLE_ATTR = "role";
    private static final String NAME = "name";
    private static final String PRODUCT_PAGE = "controller?command=particularebookview&name=name_product&id=";
    private static final String PRODUCT_NAME = "name_product";
    private static final String PRODUCT_PREFIX = "product-";
    private static final String COUNT = "count";
    private static final String PAGE = "page";

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final BasketService SERVICE = PROVIDER.getBasketService();

    public SaveProductInBasket() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        String name = request.getParameter(NAME);
        RequestDispatcher requestDispatcher = null;
        int count = Integer.parseInt(request.getSession().getAttribute(COUNT).toString());
        try {
            int idProduct = Integer.parseInt(request.getParameter(ID_PRODUCT_PARAMETER));
            if (UserRole.CUSTOMER.toString().equals(session.getAttribute(ROLE_ATTR).toString())) {
                int idUser = Integer.parseInt(session.getAttribute(ID_USER_ATTR).toString());
                SERVICE.addProduct(idProduct, idUser);
                request.getSession().setAttribute(COUNT, SERVICE.getCountProduct(idUser));
                count = SERVICE.getCountProduct(idUser);
            } else {
                Cookie cookie = new Cookie(PRODUCT_PREFIX + idProduct, String.valueOf(idProduct));
                response.addCookie(cookie);
                ++count;
            }
            request.getSession().setAttribute(COUNT, count);
            response.sendRedirect(request.getSession().getAttribute(PAGE).toString());
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PageStorage.ERROR_PAGE_PATH.getPATH());
            requestDispatcher.forward(request, response);
        }
    }
}

package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.UserRole;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.BasketService;
import by.tsarenkov.shop.service.PageStorage;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteProductFromBasket implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final BasketService SERVICE = PROVIDER.getBasketService();
    private static final String ID_PRODUCT_PARAMETER = "id";
    private static final String ID_USER_ATTR = "user";
    private static final String ROLE_ATTR = "role";
    private static final String NAME = "name";
    private static final String PRODUCT_PREFIX = "product-";
    private static final String PRODUCT_PAGE = "controller?command=particularebookview&name=name_product&id=";
    private static final String COUNT = "count";
    private static final String PAGE = "page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int idProduct = Integer.parseInt(request.getParameter(ID_PRODUCT_PARAMETER));
        HttpSession session = request.getSession(false);
        int count = Integer.parseInt(request.getSession().getAttribute(COUNT).toString());
        try {
            if (UserRole.CUSTOMER.toString().equals(session.getAttribute(ROLE_ATTR).toString())) {
                int idUser = Integer.parseInt(session.getAttribute(ID_USER_ATTR).toString());
                SERVICE.deleteProduct(idProduct, idUser);
                count = SERVICE.getCountProduct(idUser);
            } else {
                Cookie[] cookies = request.getCookies();
                response.addCookie(SERVICE.deleteProductFromCoolies(cookies, idProduct));
                --count;
            }
            request.getSession().setAttribute(COUNT, count);
        } catch (ServiceException e) {
            response.sendRedirect(PageStorage.ERROR_PAGE_PATH.getPATH());
        }
        System.out.println("controller?command=" + request.getParameter("langpage").toString());
        response.sendRedirect("controller?command=" + request.getParameter("langpage").toString());
    }
}

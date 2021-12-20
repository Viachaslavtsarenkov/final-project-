package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
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
import java.util.Locale;

public class ParticularProductView implements Command {
    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final BasketService BASKET_SERVICE = PROVIDER.getBasketService();
    private final ProductService PRODUCT_SERVICE = PROVIDER.getProductService();

    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";
    private static final String ID_PRODUCT = "id";
    private static final String PRODUCT_ATTR = "product";
    private static final String TYPE_ATTR = "type";
    private static final String BASKET_ATTR = "basket";
    private static final String ID_USER_ATTR = "user";
    private static final String ROLE_ATTR = "role";
    private static final String LANG_PAGE = "langpage";
    private static final String COMMAND = "particularebookview&id=";
    private static final String PAGE = "page";

    public ParticularProductView() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter(ID_PRODUCT));
        RequestDispatcher requestDispatcher = null;
        HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();

        try {
            Product product = PRODUCT_SERVICE.getProduct(id);
            request.setAttribute(PRODUCT_ATTR, product);
            request.setAttribute(TYPE_ATTR, product.getClass().getSimpleName().toUpperCase());
            System.out.println(product.getClass().getSimpleName().toUpperCase());
            if (UserRole.CUSTOMER.toString().equals(session.getAttribute(ROLE_ATTR).toString())) {
                int idUser = Integer.parseInt(session.getAttribute(ID_USER_ATTR).toString());
                request.setAttribute(BASKET_ATTR, BASKET_SERVICE.checkProduct(id, idUser));
            } else if (!UserRole.ADMIN.toString().equals(session.getAttribute(ROLE_ATTR).toString())) {
                request.setAttribute(BASKET_ATTR, BASKET_SERVICE.checkProductInCookies(cookies, id));
            }
            request.setAttribute(LANG_PAGE, COMMAND + id);
            request.setAttribute(PAGE, COMMAND + id);
            requestDispatcher = request.getRequestDispatcher(PageStorage.PARTICULAR_PRODUCT_PAGE_PATH.getPATH());
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
        }
        requestDispatcher.forward(request, response);
    }
}
package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.UserRole;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToOrderPage implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final BasketService SERVICE = PROVIDER.getBasketService();

    private static final String ID_USER_ATTR = "user";
    private static final String ROLE_ATTR = "role";
    private static final String ORDER = "order";
    private static final String LANG_PAGE = "langpage";

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
                dispatcher = request.getRequestDispatcher(PageStorage.ORDER_PAGE_PATH.getPATH());
                request.setAttribute(ORDER, products);
            } else {
                dispatcher = request.getRequestDispatcher(PageStorage.LOGIN_PAGE_PATH.getPATH());
            }
        } catch (ServiceException e) {
            response.sendRedirect(PageStorage.ERROR_PAGE_PATH.getPATH());
        }
        //todo add switch lang
        dispatcher.forward(request, response);
    }
}

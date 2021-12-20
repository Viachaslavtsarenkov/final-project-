package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.Product;
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
import java.util.List;

public class GoToBasketPage implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final BasketService SERVICE = PROVIDER.getBasketService();

    private static final String ID_USER_ATTR = "user";
    private static final String ROLE_ATTR = "role";
    private static final String BASKET_LIST = "basketList";
    private static final String SUM = "sum";
    private static final String LANG_PAGE = "langpage";

    public GoToBasketPage() {};

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        RequestDispatcher requestDispatcher = null;
        List<Product> products = null;
        double sum = 0;
        try {
            if (UserRole.CUSTOMER.toString().equals(session.getAttribute(ROLE_ATTR).toString())) {
                int idUser = Integer.parseInt(session.getAttribute(ID_USER_ATTR).toString());
                products = SERVICE.getAllProductsFromBasket(idUser);
                System.out.println(products.size());
            } else {
                Cookie[] cookies = request.getCookies();
                products = SERVICE.getAllProductsFromCookies(cookies);
            }
        } catch (ServiceException e) {
            response.sendRedirect(PageStorage.ERROR_PAGE_PATH.getPATH());
        }
        for(int i = 0; i < products.size(); ++i) {
            sum += products.get(i).getPrice();
        }
        request.setAttribute(BASKET_LIST, products);
        request.setAttribute(SUM, sum);
        request.setAttribute(LANG_PAGE, "gotobasketpage");
        requestDispatcher = request.getRequestDispatcher(PageStorage.BASKET_PAGE_PATH.getPATH());
        requestDispatcher.forward(request, response);
    }
}

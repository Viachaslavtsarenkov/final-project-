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

public class GoToBasketPage implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final ProductService SERVICE = PROVIDER.getProductService();

    private static final String BASKET_PAGE_PATH = "/WEB-INF/jsp/basket.jsp";
    private static final String ID_USER_ATTR = "user";
    private static final String ROLE_ATTR = "role";
    private static final String BASKET_LIST = "basketList";

    public GoToBasketPage() {};

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        List<Product> products= null;
        try {
            if (UserRole.CUSTOMER.toString().equals(session.getAttribute(ROLE_ATTR).toString())) {
                int idUser = Integer.parseInt(session.getAttribute(ID_USER_ATTR).toString());
                products = SERVICE.getAllProductsFromBasket(idUser);
            } else {
                products = SERVICE.getAllProductsFromCookies();
                //TODO GETTING products from cookies
            }
            System.out.println(products.get(0).getBrand());
            request.setAttribute(BASKET_LIST, products);
        } catch (ServiceException e) {
            //TODO ADD LOG OR ERROR PAGE
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(BASKET_PAGE_PATH);
        dispatcher.forward(request, response);
    }
}

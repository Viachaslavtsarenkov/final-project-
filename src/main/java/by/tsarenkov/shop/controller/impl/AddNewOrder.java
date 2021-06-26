package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.dao.DAOProvider;
import by.tsarenkov.shop.service.OrderService;
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

public class AddNewOrder implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final OrderService ORDER_SERVICE = PROVIDER.getOrderService();
    private final ProductService PRODUCT_SERVICE = PROVIDER.getProductService();
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final String ID_USER_ATTR = "user";

    public AddNewOrder() {
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        int idUser = Integer.parseInt(session.getAttribute(ID_USER_ATTR).toString());
        String address = "aaaadresss";
        String deliveryOption = "delivery";
        double amount = 22.22;
        List<Product> products = null;
        try {
            products = PRODUCT_SERVICE.getAllProductsFromBasket(idUser);
            ORDER_SERVICE.addNewOrder(idUser, address, deliveryOption, amount, products);
            response.sendRedirect("");
        } catch (ServiceException e) {
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }

    }
}

package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.Order;
import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.status.StatusOrder;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AddNewOrder implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final OrderService ORDER_SERVICE = PROVIDER.getOrderService();
    private final BasketService BASKET_SERVICE = PROVIDER.getBasketService();

    private static final String ID_USER_ATTR = "user";
    private static final String ADDRESS = "address";
    private static final String DELIVERY = "delivery";
    private static final String PAGE = "page";
    private static final String REDIRECT = "controller?command=gotopersonalpage&user=";

    public AddNewOrder() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Order order = new Order();
        HttpSession session = request.getSession(false);
        int idUser = Integer.parseInt(session.getAttribute(ID_USER_ATTR).toString());
        String address = request.getParameter(ADDRESS);
        System.out.println(address);
        String deliveryOption = request.getParameter(DELIVERY);
        double amount = 0;
        List<Product> products = null;
        try {
            products = BASKET_SERVICE.getAllProductsFromBasket(idUser);
            for(int i = 0; i < products.size(); ++i) {
                amount  += products.get(i).getPrice();
            }
            User user = new User();
            user.setUserId(idUser);
            order.setUser(user);
            order.setStatusOrder(StatusOrder.NEW);
            order.setAddress(address);
            order.setDeliveryOption(deliveryOption);
            order.setProducts(products);
            order.setAmount(amount);
            order.setDate(new Date());
            System.out.println(order);
            ORDER_SERVICE.addNewOrder(order);
            request.getSession().setAttribute(PAGE, REDIRECT + user.getUserId());
            BASKET_SERVICE.clearBasket(user.getUserId());
            response.sendRedirect(REDIRECT + user.getUserId());
        } catch (ServiceException e) {
            response.sendRedirect(PageStorage.ERROR_PAGE_PATH.getPATH());
        }
    }
}

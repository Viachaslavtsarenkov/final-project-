package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.BasketService;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveProductInBasket implements Command{
    private static final String idProductParameter = "id";
    private static final String idUserParameter = "id_user";
    private static final String countParameter = "count";

    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private final BasketService basketService = provider.getBasketService();

    public SaveProductInBasket() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int idProduct = 6; //Integer.parseInt(request.getParameter(idProductParameter));
        int idUser = 2;   // take from session;
        int count = 2; //Integer.parseInt(request.getParameter(countParameter));
        try {
            System.out.println("true");
            System.out.println(basketService.addProduct(idProduct, idUser, count));
        } catch (ServiceException e) {
            // to do
        }
    }
}

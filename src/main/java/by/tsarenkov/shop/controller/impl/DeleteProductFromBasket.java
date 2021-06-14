package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.dao.BasketDAO;
import by.tsarenkov.shop.service.BasketService;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductFromBasket implements Command {

    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private final BasketService basketService = provider.getBasketService();

    private static final String idProductParameter = "id_product";
    private static final String idUserParameter = "id_user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int idProduct = Integer.parseInt(request.getParameter(idProductParameter));
        int idUser = Integer.parseInt(request.getParameter(idUserParameter));
        try {
            basketService.deleteProduct(idProduct, idUser);
        } catch (ServiceException e) {
            //
        }
    }
}

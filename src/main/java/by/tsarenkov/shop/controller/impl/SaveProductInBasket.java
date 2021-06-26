package by.tsarenkov.shop.controller.impl;

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

public class SaveProductInBasket implements Command{
    private static final String ID_PRODUCT_PARAMETER = "id";
    private static final String ID_USER_ATTR = "user";
    private static final String ROLE_ATTR = "role";
    private static final String NAME = "name";
    private static final String PRODUCT_PAGE = "controller?command=particularebookview&name=name_product&id=";

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final ProductService SERVICE = PROVIDER.getProductService();

    public SaveProductInBasket() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        String name = request.getParameter(NAME);
        try {
            if (UserRole.CUSTOMER.toString().equals(session.getAttribute(ROLE_ATTR).toString())) {
                int idProduct = Integer.parseInt(request.getParameter(ID_PRODUCT_PARAMETER));
                int idUser = Integer.parseInt(session.getAttribute(ID_USER_ATTR).toString());
                int count = 2; //Integer.parseInt(request.getParameter(countParameter));
                SERVICE.addProduct(idProduct, idUser, count);
                response.sendRedirect(PRODUCT_PAGE.replace("name_product",name) + idProduct);
            } else {
                // add product in cookies
            }

        } catch (ServiceException e) {
            // todo
        }
    }
}
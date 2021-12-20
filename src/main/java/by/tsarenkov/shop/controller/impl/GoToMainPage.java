package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.UserRole;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.BasketService;
import by.tsarenkov.shop.service.PageStorage;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToMainPage implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final BasketService BASKET_SERVICE = PROVIDER.getBasketService();
    private static final String COUNT = "count";
    private static final String ID_USER_ATTR = "user";
    private static final String ROLE_ATTR = "role";
    private static final String LANG_PAGE = "langpage";
    private static final String COMMAND_PAGE = "gotomainpage";

    public GoToMainPage() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        RequestDispatcher requestDispatcher = null;
        int count = 0;
        try {
            if (UserRole.CUSTOMER.toString().equals(session.getAttribute(ROLE_ATTR).toString())) {
                int idUser = Integer.parseInt(session.getAttribute(ID_USER_ATTR).toString());
                count = BASKET_SERVICE.getCountProduct(idUser);
            } else {
                Cookie[] cookies = request.getCookies();
                count = BASKET_SERVICE.getCountProductsFromCookies(cookies);
            }
        } catch (ServiceException e) {
            response.sendRedirect(PageStorage.ERROR_PAGE_PATH.getPATH());
        }
        session.setAttribute(COUNT, count);
        request.setAttribute(LANG_PAGE, COMMAND_PAGE);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PageStorage.MAIN_PAGE_PATH.getPATH());
        dispatcher.forward(request, response);
    }


}

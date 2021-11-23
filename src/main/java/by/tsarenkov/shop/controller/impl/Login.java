package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.User;
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

public class Login implements Command {

    private static final String USER = "user";
    private static final String USER_LOGIN = "login";
    private static final String USER_PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String ACTION = "attr";
    private static final String LOGIN_ERROR = "loginError";
    private static final String COUNT = "count";
    private static final String PAGE = "page";

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final UserService USER_SERVICE = PROVIDER.getUserService();
    private static final BasketService BASKET_SERVICE = PROVIDER.getBasketService();

    public Login() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);
        int count = 0;
        User user = null;
        RequestDispatcher requestDispatcher = null;

        try {
            user = USER_SERVICE.authorization(login, password);
            HttpSession session = request.getSession(true);
            session.setAttribute(ACTION, true);
            session.setAttribute(USER, user.getUserId());
            session.setAttribute(ROLE, user.getRole());
            request.getSession().setAttribute(PAGE,"gotomainpage");
            if (user.getRole() == UserRole.CUSTOMER) {
                Cookie[] cookies = BASKET_SERVICE.transferProductsFromCookie(request.getCookies(), user.getUserId());
                for(Cookie cookie: cookies) {
                    response.addCookie(cookie);
                }
            }

            response.sendRedirect("controller?command=gotomainpage");
        } catch (ServiceException e) {
            request.setAttribute(LOGIN_ERROR, e.getMessage());
            requestDispatcher = request.getRequestDispatcher(PageStorage.LOGIN_PAGE_PATH.getPATH());
            requestDispatcher.forward(request, response);
        }

    }

}

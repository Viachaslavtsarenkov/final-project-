package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;
import by.tsarenkov.shop.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
    private static final  String INDEX_PAGE_PATH = "index.jsp";
    private static final String LOGIN_PAGE_PATH = "/WEB-INF/jsp/login.jsp";

    private static final ServiceProvider SERVICE = ServiceProvider.getInstance();
    private static final UserService USER_SERVICE = SERVICE.getUserService();
    public Login() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);

        User user = null;
        RequestDispatcher requestDispatcher = null;

        try {
            user = USER_SERVICE.authorization(login, password);

            if (user == null) {
                return;
            }
            HttpSession session = request.getSession(true);
            session.setAttribute(ACTION, true);
            session.setAttribute(USER, user.getUserId());
            session.setAttribute(ROLE, user.getRole());
            response.sendRedirect(INDEX_PAGE_PATH);
        } catch (ServiceException e) {
            request.setAttribute("loginError", e);
            requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE_PATH);
            requestDispatcher.forward(request, response);
        }
    }

}

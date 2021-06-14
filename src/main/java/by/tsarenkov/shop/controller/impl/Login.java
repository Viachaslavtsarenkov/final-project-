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

    private static final String userLogin = "login";
    private static final String userPassword = "password";
    private static final String role = "role";
    private static final String action = "attr";
    private static final  String indexPagePath = "index.jsp";
    private static final String loginPagePath = "/WEB-INF/jsp/login.jsp";

    private static final ServiceProvider service = ServiceProvider.getInstance();
    private static final UserService userService = service.getUserService();
    public Login() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(userLogin);
        String password = request.getParameter(userPassword);

        User user = null;
        RequestDispatcher requestDispatcher = null;

        try {
            user = userService.authorization(login, password);

            if (user == null) {
                return;
            }
            HttpSession session = request.getSession(true);
            session.setAttribute(action, true);
            session.setAttribute(role, user.getRole());
            System.out.println(session.getAttribute(role));
            response.sendRedirect(indexPagePath);
        } catch (ServiceException e) {
            request.setAttribute("loginError", "err");
            requestDispatcher = request.getRequestDispatcher(loginPagePath);
            requestDispatcher.forward(request, response);
        }


    }

}

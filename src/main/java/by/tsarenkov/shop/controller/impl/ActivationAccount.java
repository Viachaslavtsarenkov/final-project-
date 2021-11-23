package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.PageStorage;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;
import by.tsarenkov.shop.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActivationAccount implements Command {

    private static final String LOGIN_ACTIVATION = "login";
    private static final String CODE_ACTIVATION = "code";

    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private final UserService user = provider.getUserService();

    public ActivationAccount() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(LOGIN_ACTIVATION);
        String code = request.getParameter(CODE_ACTIVATION);
        RequestDispatcher requestDispatcher = null;
        try {
            if (user.activateAccount(login, code)){
                requestDispatcher = request.getRequestDispatcher(PageStorage.LOGIN_PAGE_PATH.getPATH());
            }
        } catch (ServiceException e) {
            response.sendRedirect(PageStorage.ERROR_PAGE_PATH.getPATH());
        }
        requestDispatcher.forward(request, response);

    }
}

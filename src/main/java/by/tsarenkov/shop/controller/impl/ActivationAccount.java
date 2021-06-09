package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;
import by.tsarenkov.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActivationAccount implements Command {

    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final UserService user = provider.getUserService();

    public ActivationAccount() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String code = request.getParameter("code");
        try {
            user.activateAccount(login, code);
        } catch (ServiceException e) {
            // to do
        }

    }
}

package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.dao.impl.SQLUserDAO;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;
import by.tsarenkov.shop.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveNewUser implements Command {

    private static final String name = "name";
    private static final String surname = "surname";
    private static final String email = "email";
    private static final String password = "password";
    private static final String phoneNumber = "phoneNumber";
    private static final String loginPage = "/WEB-INF/jsp/main.jsp";
    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final UserService userService = provider.getUserService();
    //private static final String registrationPage = "/WEB-INF/jsp/registration.jsp";

    public SaveNewUser() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UserRegistrationInfo user;
        user = new UserRegistrationInfo();
        user.setName(request.getParameter(name));
        user.setSurname(request.getParameter(surname));
        user.setEmail(request.getParameter(email));
        user.setPassword(request.getParameter(password));
        user.setPhoneNumber(request.getParameter(phoneNumber));
        RequestDispatcher dispatcher;

        try {
            if (userService.registration(user)){
                dispatcher = request.getRequestDispatcher(loginPage);
                dispatcher.forward(request, response);
            } else {
                //dispatcher = request.getRequestDispatcher(registrationPage);
            }
        } catch (ServiceException e) {

        }

    }
}

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
import java.util.HashMap;
import java.util.Map;

public class SaveNewUser implements Command {

    private static final String name = "name";
    private static final String surname = "surname";
    private static final String email = "email";
    private static final String password = "password";
    private static final String phoneNumber = "phoneNumber";
    private static final String loginPage = "/WEB-INF/jsp/main.jsp";
    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final UserService userService = provider.getUserService();
    private static final String registrationPage = "/WEB-INF/jsp/registration.jsp";
    private Map<String, String > errorValidation;

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
        RequestDispatcher dispatcher = null;

        try {
            errorValidation = userService.registration(user);
            if(errorValidation == null || errorValidation.size() == 0){
                response.sendRedirect(loginPage);
                return;
            } else {
                request.setAttribute("errorValidation", errorValidation);
                dispatcher = request.getRequestDispatcher(registrationPage);
                dispatcher.forward(request, response);
            }

        } catch (ServiceException e) {

        }
        dispatcher.forward(request, response);
    }
}

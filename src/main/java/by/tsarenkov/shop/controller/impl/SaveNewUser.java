package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.dao.UserDAO;
import by.tsarenkov.shop.service.PageStorage;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;
import by.tsarenkov.shop.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class SaveNewUser implements Command {

    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private final UserService userService = provider.getUserService();

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String REPEATED_PASSWORD = "repeated-password";
    private static final String ERROR_VALIDATION = "errorValidation";
    private static final String USER_DATA = "user";

    // todo redirect
    private static final String LOGIN_PAGE_REDIRECT = "controller?command=gotopersonalpage";


    public SaveNewUser() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Map<String, String > errorValidation = null;
        RequestDispatcher dispatcher = null;

        UserRegistrationInfo user;
        user = new UserRegistrationInfo();
        user.setName(request.getParameter(NAME));
        user.setSurname(request.getParameter(SURNAME));
        user.setEmail(request.getParameter(EMAIL));
        user.setPassword(request.getParameter(PASSWORD));
        user.setPhoneNumber(request.getParameter(PHONE_NUMBER));
        user.setRepeatedPassword(request.getParameter(REPEATED_PASSWORD));

        try {
            errorValidation = userService.registration(user);
            if(errorValidation == null || errorValidation.size() == 0){
                response.sendRedirect(LOGIN_PAGE_REDIRECT);
                return;
            } else {
                request.setAttribute(ERROR_VALIDATION, errorValidation);
                request.setAttribute(USER_DATA, user);
                dispatcher = request.getRequestDispatcher(PageStorage.REGISTRATION_PAGE_PATH.getPATH());
            }
        } catch (ServiceException e) {
            dispatcher = request.getRequestDispatcher(PageStorage.ERROR_PAGE_PATH.getPATH());
        }
        dispatcher.forward(request, response);
    }
}

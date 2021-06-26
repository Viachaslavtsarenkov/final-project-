package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.UserRole;
import by.tsarenkov.shop.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.DataInput;
import java.io.IOException;

public class GoToRegistrationPage implements Command {

    private static final String REGISTRATION_PAGE_PATH = "/WEB-INF/jsp/registration.jsp";
    private static final String MAIN_PAGE_PATH = "/WEB-INF/jsp/main.jsp";
    private static final String ROLE = "role";

    public GoToRegistrationPage() {

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher = null;

        if (session.getAttribute(ROLE).equals(UserRole.GUEST.toString())) {
            dispatcher = request.getRequestDispatcher(REGISTRATION_PAGE_PATH);

        } else {
            dispatcher = request.getRequestDispatcher(MAIN_PAGE_PATH);
        }
        dispatcher.forward(request, response);

    }
}

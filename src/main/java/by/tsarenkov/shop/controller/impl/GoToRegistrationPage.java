package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToRegistrationPage implements Command {

    private static final String PATH = "/WEB-INF/jsp/registration.jsp";

    public GoToRegistrationPage() {

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(PATH);
        dispatcher.forward(request, response);
    }
}

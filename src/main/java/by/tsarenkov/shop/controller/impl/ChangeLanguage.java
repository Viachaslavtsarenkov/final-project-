package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguage implements Command {

    private static final String mainPagePath = "/WEB-INF/jsp/main.jsp";
    private static final String locale = "local";

    public ChangeLanguage() {};

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(true).setAttribute(locale,
                request.getParameter(locale));
        request.getRequestDispatcher(mainPagePath).forward(request, response);
    }
}

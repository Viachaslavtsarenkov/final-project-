package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguage implements Command {

    private static final String MAIN_PAGE_PATH = "/WEB-INF/jsp/main.jsp";
    private static final String LOCALE = "local";

    public ChangeLanguage() {};

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(true).setAttribute(LOCALE,
                request.getParameter(LOCALE));
        request.getRequestDispatcher(MAIN_PAGE_PATH).forward(request, response);
    }
}

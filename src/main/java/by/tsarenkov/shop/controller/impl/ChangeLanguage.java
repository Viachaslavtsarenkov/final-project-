package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChangeLanguage implements Command {

    private static final String LOCALE = "local";
    private static final String PATH = "controller?command=";
    private static final String PAGE = "page";
    private final String LANG_ERROR_MSG = "Some problems with changing language";

    public ChangeLanguage() {};

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getSession(true).setAttribute(LOCALE,
                request.getParameter(LOCALE));
        String command = (String) request.getSession().getAttribute(PAGE);
        response.sendRedirect(PATH + command);

    }
}

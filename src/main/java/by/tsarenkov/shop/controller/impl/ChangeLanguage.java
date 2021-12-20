package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChangeLanguage implements Command {

    private static final String LOCALE = "local";
    private static final String PATH = "controller?command=";
    private static final String LANG_PAGE = "langpage";

    public ChangeLanguage() {};

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getSession(true).setAttribute(LOCALE,
                request.getParameter(LOCALE));
        String command = request.getParameter(LANG_PAGE);
        System.out.println(command);
        response.sendRedirect(PATH + command);
    }
}

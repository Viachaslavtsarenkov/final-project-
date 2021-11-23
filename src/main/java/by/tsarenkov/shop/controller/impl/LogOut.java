package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut implements Command {

    private static final String ROLE = "role";
    private static final String COUNT = "COUNT";
    private static final String USER = "user";
    private static final String mainPageCommand = "controller?command=gotomainpage";
    private static final String PAGE = "page";

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if(session != null) {
            session.removeAttribute(ROLE);
            session.removeAttribute(USER);
            session.removeAttribute(COUNT);
        }

        request.getSession().setAttribute(PAGE,"gotomainpage");
        response.sendRedirect(mainPageCommand);
    }
}

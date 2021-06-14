package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut implements Command {

    private static final String roleAttr = "auth";
    private static final String mainPageCommand = "Controller?command=gotomainpage&message=logout ok";

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession();

    if(session != null) {
        session.removeAttribute(roleAttr);
    }
    response.sendRedirect(mainPageCommand);
    }
}

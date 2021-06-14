package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.UserRole;
import by.tsarenkov.shop.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;

public class GoToPersonalPage implements Command {

    private static final String loginPagePath = "/WEB-INF/jsp/login.jsp";
    private static final String personalPagePath = "/WEB-INF/jsp/personalpage.jsp";


    public GoToPersonalPage() {

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        RequestDispatcher requestDispatcher = null;
        if (session.getAttribute("role") == UserRole.ADMIN.toString() ||
                session.getAttribute("role") == UserRole.CUSTOMER.toString()){
            requestDispatcher = request.getRequestDispatcher(personalPagePath);
            requestDispatcher.forward(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher(loginPagePath);
            requestDispatcher.forward(request, response);
        }
    }
}

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
    private static final String ROLE_ATTR = "role";


    public GoToPersonalPage() {

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        RequestDispatcher requestDispatcher = null;
        String role = session.getAttribute(ROLE_ATTR).toString();
        System.out.println(role);
        if (role.equals(UserRole.ADMIN.toString())||
                role.equals(UserRole.CUSTOMER.toString())){
            requestDispatcher = request.getRequestDispatcher(personalPagePath);
        } else {
            requestDispatcher = request.getRequestDispatcher(loginPagePath);
        }
        requestDispatcher.forward(request, response);
    }
}

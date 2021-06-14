package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToProductPage implements Command {

    private static final String productPath = "/WEB-INF/jsp/product_page.jsp";
    private static final String mainPath = "/WEB-INF/jsp/main.jsp";


    public GoToProductPage() {}
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher = null;
        if (session != null) {
            dispatcher = request.getRequestDispatcher(productPath);

        } else {
            dispatcher = request.getRequestDispatcher(mainPath);
        }
        dispatcher.forward(request, response);

    }
}

package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToPage implements Command {

    private static final String PATH = "/WEB-INF/jsp/";
    private static final String PAGE =  "page";

    public GoToPage() {}


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String file = request.getParameter(PAGE);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH + file);
        requestDispatcher.forward(request, response);
    }
}

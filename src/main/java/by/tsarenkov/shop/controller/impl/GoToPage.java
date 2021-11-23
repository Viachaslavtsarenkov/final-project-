package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.service.PageStorage;
import by.tsarenkov.shop.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class GoToPage implements Command {

    private static final String PATH = "/WEB-INF/jsp/";
    private static final String PAGE =  "page";

    public GoToPage() {}


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String file = request.getParameter(PAGE).toUpperCase(Locale.ROOT);
        PageStorage page = PageStorage.valueOf(file);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page.getPATH());
        requestDispatcher.forward(request, response);
    }
}

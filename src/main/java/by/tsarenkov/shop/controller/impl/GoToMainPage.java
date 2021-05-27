package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.dao.ConnectionPoolException;
import by.tsarenkov.shop.dao.ConnectionPool;
import by.tsarenkov.shop.service.UserService;
import by.tsarenkov.shop.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GoToMainPage implements Command {

    public GoToMainPage() {

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        dispatcher.forward(request, response);
    }
}

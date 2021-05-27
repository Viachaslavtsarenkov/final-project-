package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.UserRegistrationInfo;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.UserService;
import by.tsarenkov.shop.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveNewUser implements Command {

    private UserRegistrationInfo user;

    public SaveNewUser() {

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UserService service = new UserServiceImpl();
        user = new UserRegistrationInfo();
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setEmail(request.getParameter("email"));
        //user.setDateOfBirth(request.getParameter("dateOfBirth"));
        user.setPassword(request.getParameter("password"));
        service.registration(user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        dispatcher.forward(request, response);
    }
}

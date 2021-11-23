package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.PageStorage;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;
import by.tsarenkov.shop.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersView implements Command {

    private static final  String USER_LIST = "userList";
    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final UserService USER_SERVICE = PROVIDER .getUserService();

    public UsersView() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher dispatcher = null;
        List<User> userList = null;

        try {
            userList = USER_SERVICE.getAllUsers();
            System.out.println(userList);
            request.setAttribute(USER_LIST, userList);
            dispatcher = request.getRequestDispatcher(PageStorage.USERS_PAGE_PATH.getPATH());
        } catch (ServiceException e) {
            dispatcher = request.getRequestDispatcher(PageStorage.ERROR_PAGE_PATH.getPATH());
        }
        dispatcher.forward(request, response);
    }
}

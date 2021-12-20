package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.status.UserStatus;
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

public class ChangeUserStatus implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final UserService USER_SERVICE = PROVIDER .getUserService();
    private static final String ID_ATTR = "id";
    private static final String STATUS = "status";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = null;

        UserStatus status = UserStatus.valueOf(request.getParameter(STATUS).toUpperCase());
        int id = Integer.parseInt(request.getParameter(ID_ATTR));
        try {
            USER_SERVICE.changeUserStatus(id, status);
            response.sendRedirect("controller?command=gotopersonalpage&user=" + id);
        } catch (ServiceException e) {
            response.sendRedirect(PageStorage.ERROR_PAGE_PATH.getPATH());
        }
    }

}

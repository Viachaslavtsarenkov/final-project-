package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.status.StatusOrder;
import by.tsarenkov.shop.bean.status.UserStatus;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.OrderService;
import by.tsarenkov.shop.service.PageStorage;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeOrderStatus implements Command {


    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final OrderService SERVICE = PROVIDER.getOrderService();
    private static final String ID_ATTR = "id";
    private static final String STATUS = "status";
    private static final String PAGE = "page";
    private static final String CURRENT_PAGE = "particularorderview&id=";

    public ChangeOrderStatus() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = null;
        StatusOrder status = StatusOrder.valueOf(request.getParameter(STATUS).toUpperCase());
        int id = Integer.parseInt(request.getParameter(ID_ATTR));
        try {
            SERVICE.changeOrderStatus(id, status);
            response.sendRedirect("controller?command=particularorderview&id=" + id);
        } catch (ServiceException e) {
            response.sendRedirect(PageStorage.ERROR_PAGE_PATH.getPATH());
        }
        request.getSession().setAttribute(PAGE, CURRENT_PAGE + id);
    }
}

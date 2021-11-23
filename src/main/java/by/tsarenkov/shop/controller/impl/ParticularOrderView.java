package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.Order;
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
import java.util.List;

public class ParticularOrderView implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final OrderService SERVICE = PROVIDER.getOrderService();
    private static final String ORDER = "orderInf";
    private static final String ID_ORDER = "id";
    private static final String PAGE = "page";

    public ParticularOrderView() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher requestDispatcher = null;
        Order order = null;
        int id = Integer.parseInt(request.getParameter(ID_ORDER));
        try {
            order = SERVICE.getOrder(id);
            request.setAttribute(ORDER, order);
            request.getSession().setAttribute(PAGE,"particularorderview&id="+id);
            requestDispatcher = request.getRequestDispatcher(PageStorage.PARTICULAR_ORDER_PAGE_PATH.getPATH());
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PageStorage.ERROR_PAGE_PATH.getPATH());
        }

        requestDispatcher.forward(request, response);
    }
}

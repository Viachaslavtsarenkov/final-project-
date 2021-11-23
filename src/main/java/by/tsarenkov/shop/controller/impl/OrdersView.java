package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.Order;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.SearchOrderCriterion;
import by.tsarenkov.shop.bean.SearchProductСriterion;
import by.tsarenkov.shop.bean.status.StatusOrder;
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
import java.util.Locale;

public class OrdersView implements Command {


    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final OrderService SERVICE = PROVIDER.getOrderService();
    private static final String ORDERS = "ordersList";
    private static final String CURRENT_PAGE = "page";
    private static final String COUNT_PAGE = "countPage";
    private static final String STATUS = "status";
    private static final String PAGE = "page";
    private static final String SEARCH = "search";
    private static final String CRITERION = "criterionOrder";

    private int page = 1;
    private int countElements = 8;
    private int countPages;


    public OrdersView() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher requestDispatcher = null;
        SearchOrderCriterion criterion = SearchOrderCriterion
                .valueOf(request.getParameter(CRITERION).toUpperCase());
        List<Order> orders = null;
        double countAll = 0;
        String searchName = null;
        StatusOrder statusOrder = null;

        if (request.getParameter(CURRENT_PAGE) != null) {
            page = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        }

        try {
          switch (criterion) {
              case ALL:
                  countAll = SERVICE.getCountAllOrders();
                  break;
              case STATUS:
                  statusOrder = StatusOrder.valueOf(request.getParameter(STATUS).toUpperCase());
                  countAll = SERVICE.getCountAllOrdersByStatus(statusOrder);
                  request.setAttribute(STATUS, statusOrder);
                  break;
              case USER:
                  searchName = request.getParameter(SEARCH) + "%";
                  countAll = SERVICE.getCountOrdersByName(searchName);
                  request.setAttribute(SEARCH, searchName.substring(0, searchName.toString().length() - 1));
                  break;
          }
        } catch (ServiceException e) {
            response.sendRedirect(PageStorage.ERROR_PAGE_PATH.getPATH());
        }

        countPages =  (int) Math.ceil(countAll / 8);
        if (page == 1) {
            countElements = (int)Math.min(countAll, 8);
        } else if (page != countPages){
            countElements = 8;
        } else {
            countElements = (int)countAll - (page - 1) * 8;
        }
        int start = (page - 1) * 8 + 1;

        try {
            switch (criterion) {
                case ALL:
                    orders = SERVICE.getAllOrders( start, start + countElements - 1);
                    break;
                case STATUS:
                    orders = SERVICE.getAllOrdersByStatus(statusOrder,  start, start + countElements - 1);
                    break;
                case USER:
                    orders = SERVICE.getOrdersByName(searchName,  start, start + countElements - 1);
                    break;
            }
            request.setAttribute(CURRENT_PAGE, page);
            request.setAttribute(COUNT_PAGE,countPages);
            request.setAttribute(ORDERS, orders);
            request.setAttribute(CRITERION, criterion.toString());
            requestDispatcher = request.getRequestDispatcher(PageStorage.ORDERS_PAGE_PATH.getPATH());
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PageStorage.ERROR_PAGE_PATH.getPATH());
        }

        request.getSession().setAttribute(PAGE,"ordersview&page=" + page);
        requestDispatcher.forward(request, response);
    }
}
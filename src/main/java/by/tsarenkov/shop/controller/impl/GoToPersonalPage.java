package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.Order;
import by.tsarenkov.shop.bean.User;
import by.tsarenkov.shop.bean.UserRole;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToPersonalPage implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private final OrderService SERVICE = PROVIDER.getOrderService();
    private final UserService USER_SERVICE = PROVIDER.getUserService();

    private static final String USER = "user";
    private static final String ROLE_ATTR = "role";
    private static final String USER_INF = "userInf";
    private static final String ORDER_LIST = "userOrderList";
    private static final String LANG_PAGE = "langpage";
    private static final String COMMAND = "gotopersonalpage&user=";


    public GoToPersonalPage() {

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        RequestDispatcher requestDispatcher = null;
        UserRole role = UserRole.valueOf(session.getAttribute(ROLE_ATTR).toString());
        List<Order> orderList = null;
        User user = null;
        int id = 0;
        if (role == UserRole.GUEST) {
            request.setAttribute(LANG_PAGE, COMMAND);
            requestDispatcher = request.getRequestDispatcher(PageStorage.LOGIN_PAGE_PATH.getPATH());
        } else {
            if (role == UserRole.CUSTOMER) {
                id = Integer.parseInt(session.getAttribute(USER).toString());
            } else {
                id = Integer.parseInt(request.getParameter(USER));
            }
            try {
                orderList = SERVICE.getAllUserOrders(id);
                user = USER_SERVICE.getUserById(id);
                request.setAttribute(USER_INF , user);
                request.setAttribute(ORDER_LIST, orderList);
                request.setAttribute(LANG_PAGE, COMMAND + id);
                requestDispatcher = request.getRequestDispatcher(PageStorage.PERSONAL_PAGE_PATH.getPATH());
            } catch (ServiceException e) {
                response.sendRedirect(PageStorage.ERROR_PAGE_PATH.getPATH());
            }
        }
        requestDispatcher.forward(request, response);
    }
}

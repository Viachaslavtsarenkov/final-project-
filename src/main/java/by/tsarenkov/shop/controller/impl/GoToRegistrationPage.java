package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.UserRole;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.PageStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToRegistrationPage implements Command {

    private static final String ROLE = "role";
    private static final String LANG_PAGE = "langpage";
    private static final String COMMAND_PAGE = "gotoregistrationpage";

    public GoToRegistrationPage() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher = null;
        if (session.getAttribute(ROLE).equals(UserRole.GUEST.toString())) {
            dispatcher = request.getRequestDispatcher(PageStorage.REGISTRATION_PAGE_PATH.getPATH());
        } else {
            dispatcher = request.getRequestDispatcher(PageStorage.MAIN_PAGE_PATH.getPATH());
        }
        request.setAttribute(LANG_PAGE, COMMAND_PAGE);
        dispatcher.forward(request, response);

    }
}

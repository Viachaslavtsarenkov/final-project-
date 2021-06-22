package by.tsarenkov.shop.controller;

import java.io.*;
import java.util.HashMap;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final CommandProvider provider = new CommandProvider();
    private static final String ROLE_ATTR = "role";
    private static final String GUEST = "GUEST";

    public Controller() {
        super();
    }

    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Servlet s;
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        HttpSession session = request.getSession(true);
        if (session.getAttribute(ROLE_ATTR) == null) {
            session.setAttribute(ROLE_ATTR, GUEST);
        }
        String name;
        Command command;
        name = request.getParameter("command");

        command = provider.takeCommand(name);
        command.execute(request, response);
    }
}
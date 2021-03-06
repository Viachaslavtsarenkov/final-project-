package by.tsarenkov.shop.controller;

import by.tsarenkov.shop.bean.UserRole;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.*;
import java.util.HashMap;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "controller", value = "/controller")
@MultipartConfig
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
    private final CommandProvider provider = new CommandProvider();
    private static final String ROLE_ATTR = "role";
    private static final String COMMAND = "command";

    public Controller() {
        super();
    }

    public void init() {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
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
            session.setAttribute(ROLE_ATTR, UserRole.GUEST.toString());
        }
        String name;
        Command command;
        name = request.getParameter(COMMAND);
        command = provider.takeCommand(name);
        command.execute(request, response);
    }
}
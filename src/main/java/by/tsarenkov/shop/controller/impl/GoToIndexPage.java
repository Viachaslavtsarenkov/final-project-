package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToIndexPage implements Command {

    public GoToIndexPage() {}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //**
    }
}

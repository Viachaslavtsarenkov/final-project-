package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveNewProduct implements Command {

    private static final ServiceProvider provider = ServiceProvider.getInstance();
    //private static final productService

    public SaveNewProduct() {};

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

    }

}

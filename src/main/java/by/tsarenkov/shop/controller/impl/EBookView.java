package by.tsarenkov.shop.controller.impl;
import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.ProductService;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EBookView implements Command {

    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final ProductService eBookService = provider.getEBookService();
    private static final String productsPagePath = "/WEB-INF/jsp/product_page_view.jsp";

    public EBookView() {}
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = null;
        try {
            List<Product> products = eBookService.getAllProducts();
            request.setAttribute("products", products);
            System.out.println(products);
            requestDispatcher = request.getRequestDispatcher(productsPagePath);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            //log
        }
    }
}

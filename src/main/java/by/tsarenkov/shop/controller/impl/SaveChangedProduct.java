package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.characteristic.EBookCharacteristic;
import by.tsarenkov.shop.bean.status.ProductStatus;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.ProductService;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SaveChangedProduct implements Command {

    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final ProductService service = provider.getProductService();

    private static final String ID_PRODUCT = "id";
    private static final String BRAND  = "brand";
    private static final String COUNT = "count";
    private static final String PRICE = "price";
    private static final String STATUS = "status";
    private static final String PATH = "path";

    private static final String PRODUCT_PAGE = "controller?command=particularebookview&id="; // change

    public SaveChangedProduct() {}
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = null;
        int idProduct = Integer.parseInt(request.getParameter(ID_PRODUCT));
        String brand = request.getParameter(BRAND);
        int count = Integer.parseInt(request.getParameter(COUNT));
        double price = Double.parseDouble(request.getParameter(PRICE));
        ProductStatus status = ProductStatus.valueOf(request.getParameter(STATUS).toUpperCase());
        String path = PATH;
        try {
            Map<String, String> characteristics = new HashMap<>();
            EBookCharacteristic[] eBookCharacteristics = EBookCharacteristic.values();
            for (EBookCharacteristic eBook : eBookCharacteristics) {
                String value = request.getParameter(eBook.toString().toLowerCase());
                if (value != null) {
                    characteristics.put(eBook.toString(), value);
                }
            }
            if (service.changeProduct(idProduct, brand, count, price, status, path, characteristics)) {
               response.sendRedirect(PRODUCT_PAGE + idProduct);
            }
            } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PRODUCT_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}

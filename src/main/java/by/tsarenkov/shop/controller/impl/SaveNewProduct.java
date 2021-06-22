package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.characteristic.EBookCharacteristic;
import by.tsarenkov.shop.bean.characteristic.LaptopCharacteristic;
import by.tsarenkov.shop.bean.characteristic.SmartphoneCharacteristic;
import by.tsarenkov.shop.bean.characteristic.TabletCharacteristic;
import by.tsarenkov.shop.bean.good.Tablet;
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
import java.io.PipedReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveNewProduct implements Command {

    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final ProductService service = provider.getProductService();

    private static final String PRODUCT_ATTR = "products";
    private static final String CATEGORY = "id_category";
    private static final String BRAND  = "brand";
    private static final String COUNT = "count";
    private static final String PRICE = "price";
    private static final String STATUS = "status";
    private static final String PATH = "path";
    private static final String NAME = "name";

    private static final String PRODUCTS_PAGE = "controller?command=gotomainpage"; // change
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";
    public SaveNewProduct() {};

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher requestDispatcher = null;
        int idCategory = Integer.parseInt(request.getParameter(CATEGORY));
        String brand = request.getParameter(BRAND);
        int count = Integer.parseInt(request.getParameter(COUNT));
        double price = Double.parseDouble(request.getParameter(PRICE));
        ProductStatus status = ProductStatus.EXPECTED;
        //ProductStatus.valueOf(request.getParameter(STATUS).toUpperCase());
        String path = PATH;
        ProductName name = ProductName.valueOf(request.getParameter(NAME).toUpperCase());
        Map<String, String> characteristics = new HashMap<>();
        switch (name) {
            case EBOOK:
                EBookCharacteristic[] eBookCharacteristics = EBookCharacteristic.values();
                for (EBookCharacteristic eBook : eBookCharacteristics) {
                    String value = request.getParameter(eBook.toString().toLowerCase());
                    if (value != null) {
                        characteristics.put(eBook.toString(), value);
                    }
                }
                break;
            case TABLET:
                TabletCharacteristic[] tabletCharacteristics = TabletCharacteristic.values();
                for (TabletCharacteristic eBook : tabletCharacteristics) {
                    String value = request.getParameter(eBook.toString().toLowerCase());
                    if (value != null) {
                        characteristics.put(eBook.toString(), value);
                    }
                }
                break;
            case SMARTPHONE:
                SmartphoneCharacteristic[] smartphoneCharacteristics =  SmartphoneCharacteristic.values();
                for ( SmartphoneCharacteristic smartphone : smartphoneCharacteristics) {
                    String value = request.getParameter(smartphone.toString().toLowerCase());
                    if (value != null) {
                        characteristics.put(smartphone.toString(), value);
                    }
                }
                break;
            case LAPTOP:
                LaptopCharacteristic[] laptopCharacteristics =  LaptopCharacteristic.values();
                for (LaptopCharacteristic laptop : laptopCharacteristics) {
                    String value = request.getParameter(laptop.toString().toLowerCase());
                    if (value != null) {
                        characteristics.put(laptop.toString(), value);
                    }
                }
                break;
        }
        try {
            if (service.addNewProduct(idCategory, brand, count, price, status, path, characteristics)) {
                response.sendRedirect(PRODUCTS_PAGE);
            }
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }




    }

}

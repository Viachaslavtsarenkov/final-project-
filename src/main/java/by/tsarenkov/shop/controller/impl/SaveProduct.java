package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.controller.CharacteristicProducts;
import by.tsarenkov.shop.service.PageStorage;
import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.status.ProductStatus;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.controller.CommandName;
import by.tsarenkov.shop.service.ProductService;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Map;

public class SaveProduct implements Command {

    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private final ProductService service = provider.getProductService();

    private static final String CATEGORY = "id_category";
    private static final String BRAND  = "brand";
    private static final String MODEL  = "model";
    private static final String COUNT = "count";
    private static final String PRICE = "price";
    private static final String STATUS = "status";
    private static final String PATH = "path";
    private static final String NAME = "name";
    private static final String COMMAND = "command";
    private static final String ID_PRODUCT = "id";
    private static final String PHOTO = "photo";
    private static final String PRODUCT_NAME = "product_name";

    private static final String PRODUCTS_PAGE = "controller?command=gotomainpage";
    private static final String PRODUCT_PAGE = "controller?command=particularebookview&name=product_name&id=";

    public SaveProduct() {};

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher requestDispatcher = null;
        Part photo = request.getPart(PHOTO);
        InputStream fileContent = photo.getInputStream();

        Product product = new Product();
        product.setBrand(request.getParameter(BRAND));
        product.setCount(Integer.parseInt(request.getParameter(COUNT)));
        product.setPrice(Double.valueOf(request.getParameter(PRICE)));
        product.setModel(request.getParameter(MODEL));
        product.setStatus(ProductStatus.valueOf(request.getParameter(STATUS)));

        ProductName name = ProductName.valueOf(request.getParameter(NAME).toUpperCase());
        Map<String, String> characteristics = CharacteristicProducts.extractCharacteristic(name, request);

        try {
            String path = "/images/";
            CommandName command = CommandName.valueOf(request.getParameter(COMMAND).toUpperCase());
            if (command == CommandName.SAVENEWPRODUCT) {
                int idCategory = Integer.parseInt(request.getParameter(CATEGORY));
                if (photo.getSize() == 0) {
                    path = "default.png";
                }
                if (service.addNewProduct(idCategory, product, fileContent,
                        path, characteristics)) {
                    response.sendRedirect(PRODUCTS_PAGE);
                }
            } else {
                product.setId(Integer.parseInt(request.getParameter(ID_PRODUCT)));
                if (photo.getSize() == 0) {
                    path = null;
                }
                if (service.changeProduct(name, product,
                        fileContent, path, characteristics)) {
                    response.sendRedirect(PRODUCT_PAGE
                            .replace(PRODUCT_NAME, name.toString()) + product.getId());
                }
            }
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PageStorage.ERROR_PAGE_PATH.getPATH());
            requestDispatcher.forward(request, response);
        }
    }


}
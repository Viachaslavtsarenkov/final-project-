package by.tsarenkov.shop.controller.impl;

import by.tsarenkov.shop.bean.characteristic.EBookCharacteristic;
import by.tsarenkov.shop.bean.good.EBook;
import by.tsarenkov.shop.controller.Command;
import by.tsarenkov.shop.service.ProductService;
import by.tsarenkov.shop.service.ServiceException;
import by.tsarenkov.shop.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SaveNewProduct implements Command {

    private static final String category = "id_category";

    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final ProductService service = provider.getEBookService();


    public SaveNewProduct() {};

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idCategory = Integer.parseInt(request.getParameter(category));
            Map<String, String> characteristics = new HashMap<>();
            EBookCharacteristic[] eBookCharacteristics = EBookCharacteristic.values();
            for (EBookCharacteristic eBook : eBookCharacteristics) {
                String value = request.getParameter(eBook.toString().toLowerCase());
                if (value != null) {
                    characteristics.put(eBook.toString(), value);
                }
            }
            service.addNewProduct(characteristics);
        } catch (ServiceException e) {
            // to do
        }

    }

}

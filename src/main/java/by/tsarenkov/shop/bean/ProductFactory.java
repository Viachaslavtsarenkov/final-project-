package by.tsarenkov.shop.bean;


import by.tsarenkov.shop.bean.good.EBook;
import by.tsarenkov.shop.bean.good.Laptop;
import by.tsarenkov.shop.bean.good.Smartphone;
import by.tsarenkov.shop.bean.good.Tablet;
import by.tsarenkov.shop.bean.status.ProductStatus;

import java.util.Map;


public class ProductFactory {

    public ProductFactory() {}

    public static Product getProduct(ProductName name, int id, String brand,
                                     int count, double price,
                                     ProductStatus status, String path,
                                     Map<String, String> productCharacteristic) {
        Product product = null;
        switch (name) {
            case EBOOK:
                product = new EBook.EBookBuilder(id, brand, count, price, status, path)
                        .setCharacteristics(productCharacteristic).getInstance();
                break;
            case LAPTOP:
                product = new Laptop.LaptopBuilder(id, brand, count, price, status, path)
                       .setCharacteristics(productCharacteristic).getInstance();
                break;
            case TABLET:
                product = new Tablet.TabletBuilder(id, brand, count, price, status, path)
                        .setCharacteristics(productCharacteristic).getInstance();
                break;
            case SMARTPHONE:
                product = new Smartphone.SmartphoneBuilder(id, brand, count, price, status, path)
                        .setCharacteristics(productCharacteristic).getInstance();
            break;
        }
        return product;
    }
}

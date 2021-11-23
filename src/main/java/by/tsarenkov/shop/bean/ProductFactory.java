package by.tsarenkov.shop.bean;


import by.tsarenkov.shop.bean.good.EBook;
import by.tsarenkov.shop.bean.good.Laptop;
import by.tsarenkov.shop.bean.good.Smartphone;
import by.tsarenkov.shop.bean.good.Tablet;
import by.tsarenkov.shop.bean.status.ProductStatus;

import java.util.Map;


public class ProductFactory {

    public ProductFactory() {}

    public static Product getProduct(ProductName name, Product product,
                                     Map<String, String> productCharacteristic) {
        Product currentProduct = null;

        switch (name) {
            case EBOOK:
                currentProduct  = new EBook.EBookBuilder()
                        .setId(product.getId())
                        .setBrand(product.getBrand())
                        .setModel(product.getModel())
                        .setCount(product.getCount())
                        .setPrice(product.getPrice())
                        .setStatus(product.getStatus())
                        .setPath(product.getPath())
                        .setCharacteristics(productCharacteristic)
                        .getInstance();
                break;
            case LAPTOP:
                currentProduct = new Laptop.LaptopBuilder()
                        .setId(product.getId())
                        .setBrand(product.getBrand())
                        .setModel(product.getModel())
                        .setCount(product.getCount())
                        .setPrice(product.getPrice())
                        .setStatus(product.getStatus())
                        .setPath(product.getPath())
                        .setCharacteristics(productCharacteristic)
                        .getInstance();
                break;
            case TABLET:
                currentProduct = new Tablet.TabletBuilder()
                          .setId(product.getId())
                          .setBrand(product.getBrand())
                          .setModel(product.getModel())
                          .setCount(product.getCount())
                          .setPrice(product.getPrice())
                          .setStatus(product.getStatus())
                          .setPath(product.getPath())
                          .setCharacteristics(productCharacteristic)
                          .getInstance();
                break;
            case SMARTPHONE:
                currentProduct = new Smartphone.SmartphoneBuilder()
                          .setId(product.getId())
                          .setBrand(product.getBrand())
                          .setModel(product.getModel())
                          .setCount(product.getCount())
                          .setPrice(product.getPrice())
                          .setStatus(product.getStatus())
                          .setPath(product.getPath())
                          .setCharacteristics(productCharacteristic)
                          .getInstance();
            break;
        }
        return currentProduct;
    }
}

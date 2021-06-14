package by.tsarenkov.shop.bean;


import by.tsarenkov.shop.bean.good.EBook;
import by.tsarenkov.shop.bean.good.Laptop;

import java.util.Map;


public class ProductFactory {

    public ProductFactory() {}

    public static Product getProduct(ProductName name, int id, Map<String, String> characteristics) {
        Product product = null;
        switch (name) {
            case EBOOK:
                product = new EBook.EBookBuilder(id, characteristics).getInstance();
                break;
            case LAPTOP:
                product = new Laptop.LaptopBuilder(id, characteristics).getInstance();
                break;
            case TABLET:
                int k = 1;
                break;
            case SMARTPHONE:
                int i = 0;
            break;
        }
        return product;
    }
}

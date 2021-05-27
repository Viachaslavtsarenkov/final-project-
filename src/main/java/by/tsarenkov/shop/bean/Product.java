package by.tsarenkov.shop.bean;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private double price;

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

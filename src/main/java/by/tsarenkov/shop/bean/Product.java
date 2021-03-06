package by.tsarenkov.shop.bean;

import by.tsarenkov.shop.bean.status.ProductStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Product implements Serializable {
    private int id;
    private String brand;
    private int count;
    private ProductStatus status;
    private String path;
    private double price;
    private String model;

    public Product() {}

    public Product(int id, String brand, String model,
                   int count, double price,
                   ProductStatus status, String path) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.count = count;
        this.price = price;
        this.path = path;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && count == product.count
                && Double.compare(product.price, price) == 0
                && Objects.equals(brand, product.brand)
                && status == product.status
                && Objects.equals(path, product.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, count, status, path, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", count=" + count +
                ", status=" + status +
                ", path='" + path + '\'' +
                ", price=" + price +
                '}';
    }
}

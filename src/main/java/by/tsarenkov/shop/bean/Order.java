package by.tsarenkov.shop.bean;

import by.tsarenkov.shop.bean.status.StatusOrder;
import by.tsarenkov.shop.service.ProductService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {

    private int idOrder;
    private double price;
    private String deliveryOption;
    private StatusOrder statusOrder;
    private String address;
    private int userId;
    private List<Product> products;

    public Order() {
        products = new ArrayList<>();
    }

    public Order(int idOrder, int userId, String address,
                 String deliveryOption,
                  double amount, StatusOrder statusOrder) {
        this.idOrder = idOrder;
        this.userId = userId;
        this.deliveryOption = deliveryOption;
        this.statusOrder = statusOrder;
        this.address = address;
        this.price = price;
        products = new ArrayList<>();
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDeliveryOption() {
        return deliveryOption;
    }

    public void setDelivery_option(String delivery_option) {
        this.deliveryOption = delivery_option;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserID(int userID) {
        this.userId= userID;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;

        return idOrder == order.idOrder &&
                Double.compare(order.price, price) == 0 &&
                userId == order.userId &&
                Objects.equals(deliveryOption, order.deliveryOption) &&
                statusOrder == order.statusOrder &&
                Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder,
                price, deliveryOption,
                statusOrder, address, userId, products);
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", price=" + price +
                ", deliveryOption='" + deliveryOption + '\'' +
                ", statusOrder=" + statusOrder +
                ", address='" + address + '\'' +
                ", userId=" + userId +
                ", products=" + products +
                '}';
    }
}

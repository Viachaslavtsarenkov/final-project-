package by.tsarenkov.shop.bean;

import by.tsarenkov.shop.bean.status.StatusOrder;
import by.tsarenkov.shop.service.ProductService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {

    private int idOrder;
    private User user;
    private double amount;
    private String deliveryOption;
    private StatusOrder statusOrder;
    private String address;
    private List<Product> products;
    private Date date;

    public Order() {
        products = new ArrayList<>();
    }

    public Order(int idOrder, User user, String address,
                 String deliveryOption,
                  double amount, StatusOrder statusOrder, Date date) {
        this.idOrder = idOrder;
        this.user = user;
        this.deliveryOption = deliveryOption;
        this.statusOrder = statusOrder;
        this.address = address;
        this.amount = amount;
        this.date = date;
        products = new ArrayList<>();
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double price) {
        this.amount = price;
    }

    public String getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(String delivery_option) {
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

    public User getUser() {
        return user;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                Double.compare(order.amount, amount) == 0 &&
                user == order.user &&
                Objects.equals(deliveryOption, order.deliveryOption) &&
                statusOrder == order.statusOrder &&
                Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder,
                amount, deliveryOption,
                statusOrder, address, user, products);
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", amount=" + amount +
                ", deliveryOption='" + deliveryOption + '\'' +
                ", statusOrder=" + statusOrder +
                ", address='" + address + '\'' +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}

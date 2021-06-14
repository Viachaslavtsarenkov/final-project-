package by.tsarenkov.shop.bean;

import by.tsarenkov.shop.bean.status.StatusOrder;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {

    private int idOrder;
    private int count;
    private double price;
    private String delivery_option;
    private StatusOrder statusOrder;
    private String address;
    private int userID;
    private int goodID;

    public Order() {}

    public Order(int idOrder, int userID,
                 int goodID, String delivery_option,
                 StatusOrder statusOrder, String address,
                 int count, double price) {
        this.idOrder = idOrder;
        this.userID = userID;
        this.goodID = goodID;
        this.delivery_option = delivery_option;
        this.statusOrder = statusOrder;
        this.address = address;
        this.count = count;
        this.price = price;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDelivery_option() {
        return delivery_option;
    }

    public void setDelivery_option(String delivery_option) {
        this.delivery_option = delivery_option;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getGoodID() {
        return goodID;
    }

    public void setGoodID(int goodID) {
        this.goodID = goodID;
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
                count == order.count &&
                Double.compare(order.price, price) == 0 &&
                userID == order.userID &&
                goodID == order.goodID &&
                Objects.equals(delivery_option, order.delivery_option) &&
                statusOrder == order.statusOrder &&
                Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, count, price, delivery_option, statusOrder, address, userID, goodID);
    }
}

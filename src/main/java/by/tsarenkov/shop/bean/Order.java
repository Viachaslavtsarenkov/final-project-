package by.tsarenkov.shop.bean;

import java.io.Serializable;

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
}

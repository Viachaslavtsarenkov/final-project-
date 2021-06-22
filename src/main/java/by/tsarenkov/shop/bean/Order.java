package by.tsarenkov.shop.bean;

import by.tsarenkov.shop.bean.status.StatusOrder;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {

    private int idOrder;
    private int count;
    private double price;
    private String deliveryOption;
    private StatusOrder statusOrder;
    private String address;
    private int userId;
    private int goodId;

    public Order() {}

    public Order(int idOrder, int userId,
                 int goodId, String deliveryOption,
                 StatusOrder statusOrder, String address,
                 int count, double price) {
        this.idOrder = idOrder;
        this.userId = userId;
        this.goodId = goodId;
        this.deliveryOption = deliveryOption;
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

    public int getGoodId() {
        return goodId;
    }

    public void setGoodID(int goodID) {
        this.goodId = goodID;
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
                userId == order.userId &&
                goodId == order.goodId &&
                Objects.equals(deliveryOption, order.deliveryOption) &&
                statusOrder == order.statusOrder &&
                Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, count, price, deliveryOption, statusOrder, address, userId, goodId);
    }
}

package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.*;
import by.tsarenkov.shop.bean.status.ProductStatus;
import by.tsarenkov.shop.bean.status.StatusOrder;
import by.tsarenkov.shop.bean.status.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructorDAO {

    private static final String ID_COLUMN = "id_user";
    private static final String USERNAME_COLUMN  = "username";
    private static final String SURNAME_COLUMN  = "surname";
    private static final String EMAIL_COLUMN  = "email";
    private static final String PASSWORD_COLUMN  = "password";
    private static final String ROLE_COLUMN  = "user_role";
    private static final String PHONE_COLUMN  = "phone";
    private static final String STATUS_COLUMN  = "status";

    private static final String NAME = "name";
    private static final String[] GOODS_ID = new String[]{"good_id"};
    private static final String BRAND = "brand";
    private static final String CHARACTERISTIC = "characteristic";
    private static final String GOOD_ID = "goods_id";
    private static final String VALUE = "value";
    private static final String COUNT = "count";
    private static final String PRICE = "price";
    private static final String PRODUCT_STATUS = "goods.status";
    private static final String PATH = "path";
    private static final String GOOD_ID_COLUMN = "goods_goods_id";
    private static final String NAME_CATEGORY = "name_category";

    private static final String ORDER = "order";
    private static final String ID_ORDER_COLUMN = "id_order";
    private static final String ID_USER_COLUMN = "user_id_user";
    private static final String ADDRESS_COLUMN = "address";
    private static final String DELIVERY_COLUMN = "delivery_option";
    private static final String AMOUNT_COLUMN = "sum";
    private static final String DATE_COLUMN = "date";

    public ConstructorDAO() {}

    protected static User constructUser(ResultSet resultSet) {
        User user = new User();
        try {
            resultSet.next();
            user.setUserId(resultSet.getInt(ID_COLUMN));
            user.setName(resultSet.getString(USERNAME_COLUMN));
            user.setSurname(resultSet.getString(SURNAME_COLUMN));
            user.setEmail(resultSet.getString(EMAIL_COLUMN));
            user.setPassword(resultSet.getString(PASSWORD_COLUMN));
            user.setPhoneNumber(resultSet.getString(PHONE_COLUMN));
            user.setRole(UserRole.valueOf(resultSet.getString(ROLE_COLUMN)));
            user.setStatus(UserStatus.valueOf(resultSet.getString(STATUS_COLUMN)));
        } catch (SQLException e) {
            // todo
        }
        return user;
    }

    protected static Product constructProductByResultSet(ResultSet resultSet) {
        Product product = new Product();
        String characteristic = null;
        String value = null;
        ProductName name = null;
        Map<String, String> characteristics = new HashMap<>();

        try {
            while (resultSet.next()) {
                characteristic = resultSet.getString(CHARACTERISTIC);
                value = resultSet.getString(VALUE);
                if (resultSet.isFirst()) {
                    product.setCount(resultSet.getInt(COUNT));
                    product.setPrice(resultSet.getDouble(PRICE));
                    product.setBrand(resultSet.getString(NAME));
                    product.setStatus(ProductStatus.valueOf(resultSet.getString(PRODUCT_STATUS)));
                    product.setPath(resultSet.getString(PATH));
                    product.setId(resultSet.getInt(GOOD_ID));
                    name = ProductName.valueOf(resultSet.getString(NAME_CATEGORY));
                }

                characteristics.put(characteristic.toUpperCase(), value);

                if (resultSet.isLast()) {
                    product = ProductFactory
                            .getProduct(name, product, characteristics);
                }
            }
        } catch (SQLException e) {
            //todo
        }
        return product;
    }

    protected static List<Product> constructAllProductByResultSet(ResultSet resultSet) {
        Product product = null;
        List<Product> allProducts = new ArrayList<>();
        String characteristic = null;
        String value = null;
        ProductName name = null;
        Map<String, String> characteristics = new HashMap<>();

        int currentId = 0;
        try {
            while (resultSet.next()) {
                int productId = resultSet.getInt(GOOD_ID);
                if (currentId != productId) {
                    if (currentId != 0) {
                        product = ProductFactory.getProduct(name, product, characteristics);
                        allProducts.add(product);
                    }
                    product = new Product();
                    product.setId(resultSet.getInt(GOOD_ID));
                    product.setCount(resultSet.getInt(COUNT));
                    product.setBrand(resultSet.getString(NAME));
                    product.setPrice(resultSet.getDouble(PRICE));
                    product.setStatus(ProductStatus.valueOf(resultSet.getString(PRODUCT_STATUS)));
                    product.setPath(resultSet.getString(PATH));
                    name = ProductName.valueOf(resultSet.getString(NAME_CATEGORY));
                    currentId = productId;
                }

                characteristic = resultSet.getString(CHARACTERISTIC);
                value = resultSet.getString(VALUE);
                characteristics.put(characteristic.toUpperCase(), value);

                if(resultSet.isLast()) {
                    product = ProductFactory.getProduct(name, product, characteristics);
                    allProducts.add(product);
                }
            }
        } catch (SQLException e) {
            //todo
        }
        return allProducts;
    }

    protected static List<Order> constructAllOrdersByResultSet(ResultSet resultSet) {
        List<Order> orderList = new ArrayList<>();
        Product product = null;
        Order order = null;
        List<Product> allProducts = new ArrayList<>();
        String characteristic = null;
        String value = null;
        ProductName name = null;
        Map<String, String> characteristics = new HashMap<>();
        int currentIdProduct = 0;
        int currentIdOrder = 0;

        try {
            while (resultSet.next()) {
                int orderId = resultSet.getInt(ID_ORDER_COLUMN);
                if (currentIdOrder != orderId) {
                    if (currentIdOrder != 0) {
                        order = new Order();
                        order.setProducts(allProducts);
                        orderList.add(order);
                    }
                    order = new Order();
                    order.setIdOrder(orderId);
                    order.setUserID(resultSet.getInt(ID_USER_COLUMN));
                    order.setAmount(resultSet.getInt(AMOUNT_COLUMN));
                    order.setDeliveryOption(resultSet.getString(DELIVERY_COLUMN));
                    order.setDate(resultSet.getDate(DATE_COLUMN));
                    order.setStatusOrder(StatusOrder.valueOf(resultSet.getString(STATUS_COLUMN)));
                    order.setAddress(resultSet.getString(ADDRESS_COLUMN));
                    currentIdOrder = orderId;
                }

                int productId = resultSet.getInt(GOOD_ID);
                if (currentIdProduct != productId) {
                    System.out.println(currentIdProduct);
                    if (currentIdProduct!= 0) {
                        product = ProductFactory.getProduct(name, product, characteristics);
                        allProducts.add(product);
                        System.out.println();
                    }
                    product = new Product();
                    product.setId(resultSet.getInt(GOOD_ID));
                    product.setCount(resultSet.getInt(COUNT));
                    product.setBrand(resultSet.getString(NAME));
                    product.setPrice(resultSet.getDouble(PRICE));
                    product.setStatus(ProductStatus.valueOf(resultSet.getString(PRODUCT_STATUS)));
                    product.setPath(resultSet.getString(PATH));
                    name = ProductName.valueOf(resultSet.getString(NAME_CATEGORY));
                    currentIdProduct = productId;
                }

                characteristic = resultSet.getString(CHARACTERISTIC);
                value = resultSet.getString(VALUE);
                characteristics.put(characteristic.toUpperCase(), value);

                if(resultSet.isLast()) {
                    product = ProductFactory.getProduct(name, product, characteristics);
                    allProducts.add(product);
                    order.setProducts(allProducts);
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            //todo
        }
        System.out.println(orderList);
        return orderList;
    }


}

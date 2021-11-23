package by.tsarenkov.shop.dao;

public class QueriesStorage {

    public QueriesStorage() {}


    //user queries
    public static final String REGISTRATION = "INSERT INTO store.user (username, surname, email, "
            + "status, password, user_role, phone, code)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GETTING_USER_BY_EMAIL = "SELECT username FROM user WHERE email = ?";
    public static final String ACTIVATION_USER = "UPDATE user SET status = ? WHERE email = ? " +
            "AND code = ? AND status = 'NO_ACTIVATED'";
    public static final String AUTHORIZATION = "SELECT * FROM user WHERE email = ? AND password = ?";

    //products queries
    public static final String SAVE_PRODUCT_QUERY = "INSERT INTO goods " +
            "(name, categories_id_category, count, price, status, path) VALUES(?,?,?,?,?,?)";

    public static final String SAVE_CHARACTERISTIC_QUERY = "INSERT INTO goods_characteristic" +
            "(goods_goods_id, characteristic, value) values(?,?,?)";

    public  static final String GET_ALL_PRODUCT_QUERY = "SELECT name_category, goods.goods_id," +
            " goods.name, goods.count, goods.price, goods.status, goods.path,\n" +
            "goods_characteristic.characteristic, goods_characteristic.value\n" +
            "FROM goods\n" +
            "inner join goods_characteristic\n" +
            "on goods_id = goods_goods_id\n" +
            "inner join categories\n" +
            "on id_category= categories_id_category\n" +
            "where name_category = ?";

    public static final String COUNT_PRODUCTS = "SELECT COUNT(*) AS count from store.GOODS\n" +
            "INNER JOIN store.categories\n" +
            "ON categories_id_category = id_category\n" +
            "WHERE name_category = ?";

    public static final String GET_PRODUCT_QUERY_BY_ID = "SELECT name_category, goods.goods_id,\n" +
            "            goods.name, goods.count, goods.price, goods.status, goods.path,\n" +
            "            goods_characteristic.characteristic, goods_characteristic.value\n" +
            "            FROM goods\n" +
            "            inner join goods_characteristic\n" +
            "            on goods_id = goods_goods_id\n" +
            "            inner join categories\n" +
            "            on id_category= categories_id_category\n" +
            "            where goods_id = ?";

    public static final String UPDATE_PRODUCT_QUERY = "UPDATE goods SET name = ?," +
            "count = ?, price = ?, status = ?, path = ?\n" +
            "WHERE goods_id = ? ";

    public static final String UPDATE_CHARACTERISTICS_QUERY = "UPDATE goods_characteristic SET\n" +
            "value = ? " +
            "WHERE goods_goods_id = ? and characteristic = ?";

    public static final String ADD_PRODUCT_QUERY = "INSERT INTO BASKET (goods_goods_id, user_id_user, count)\n" +
            "VALUES(?, ?, ?)";
    public static final String DELETE_PRODUCT_QUERY = "DELETE FROM BASKET WHERE goods_goods_id = ? AND user_id_user = ?";
    public static final String GET_BASKET_PRODUCT = "SELECT goods_goods_id, name_category from store.basket\n" +
            "INNER JOIN store.goods\n" +
            "ON goods_id = goods_goods_id\n" +
            "INNER JOIN store.categories\n" +
            "ON categories_id_category = id_category\n" +
            "INNER JOIN store.user\n" +
            "ON id_user =  ?";

    public static final String CHECK_PRODUCT_QUERY = "SELECT EXISTS(SELECT id FROM BASKET WHERE " +
            "goods_goods_id = ? AND user_id_user = ?)";
}

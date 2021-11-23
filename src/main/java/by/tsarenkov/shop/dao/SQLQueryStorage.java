package by.tsarenkov.shop.dao;

public class SQLQueryStorage {

    public SQLQueryStorage() {}


    //user queries
    public static final String REGISTRATION = "INSERT INTO store.user (username, surname, email, "
            + "status, password, user_role, phone, code)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GETTING_USER_BY_EMAIL = "SELECT username FROM user WHERE email = ?";
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id_user = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM user where user_role = 'CUSTOMER'";
    public static final String ACTIVATION_USER = "UPDATE user SET status = ? WHERE email = ? " +
            "AND code = ? AND status = 'NO_ACTIVATED'";
    public static final String AUTHORIZATION = "SELECT * FROM ffff user WHERE email = ? AND password = ?";
    public static final String CHANGE_USER_STATUS = "UPDATE user SET status = ? WHERE id_user = ?";


    //products queries
    public static final String SAVE_PRODUCT_QUERY = "INSERT INTO goods " +
            "(name, categories_id_category, count, price, status, path, model) VALUES(?,?,?,?,?,?,?)";

    public static final String SAVE_CHARACTERISTIC_QUERY = "INSERT INTO goods_characteristic" +
            "(goods_goods_id, characteristic, value) values(?,?,?)";

    public  static final String GET_ALL_PRODUCT_QUERY = "Select * from goods_characteristic\n" +
            "inner join (\n" +
            "SELECT goods_id, rownumber\n" +
            "from (SELECT goods_id, row_number() over(ORDER BY goods_id) AS RowNumber FROM store.goods\n" +
            "inner join store.categories\n" +
            "on id_category = categories_id_category\n" +
            "where name_category = ?) as k\n" +
            "where k.rownumber between ? and ?) as table1\n" +
            "on goods_characteristic.goods_goods_id = table1.goods_id\n" +
            "inner join goods\n" +
            "on goods.goods_id = goods_characteristic.goods_goods_id\n" +
            "inner join categories\n" +
            "on goods.categories_id_category = categories.id_category";

    public static final String COUNT_ALL_PRODUCTS = "SELECT count(*) as count from goods\n" +
            "inner join categories \n" +
            "on id_category = goods.categories_id_category\n" +
            "where name_category = ?";

    public static final String GET_ALL_PRODUCTS_BY_NAME = "Select * from goods_characteristic\n" +
            "inner join (\n" +
            "SELECT goods_id, rownumber\n" +
            "from (SELECT goods_id, row_number() over(ORDER BY goods_id) AS RowNumber FROM store.goods\n" +
            "inner join store.categories\n" +
            "on id_category = categories_id_category\n" +
            "where concat(name, ' ', model) like ?) as k\n" +
            "where k.rownumber between ? and ?) as table1\n" +
            "on goods_characteristic.goods_goods_id = table1.goods_id\n" +
            "inner join goods\n" +
            "on goods.goods_id = goods_characteristic.goods_goods_id\n" +
            "inner join categories\n" +
            "on goods.categories_id_category = categories.id_category";

    public static final String GET_COUNT_ALL_PRODUCTS_BY_NAME = "SELECT count(*) as count FROM store.goods\n" +
            "where concat(name, ' ', model) like ?";

    public  static final String GET_PRODUCTS_BY_STATUS = "SELECT name_category, goods.goods_id," +
            " goods.name, goods.count, goods.price, goods.status, goods.path,\n" +
            "goods_characteristic.characteristic, goods_characteristic.value, goods.model\n" +
            "FROM goods\n" +
            "inner join goods_characteristic\n" +
            "on goods_id = goods_goods_id\n" +
            "inner join categories\n" +
            "on id_category= categories_id_category\n" +
            "where name_category = ?" +
            "and status = ?";

    public static final String GET_PRODUCT_QUERY_BY_ID = "SELECT name_category, goods.goods_id,\n" +
            "goods.name, goods.count, goods.price, goods.status, goods.path,\n" +
            "goods_characteristic.characteristic, goods_characteristic.value, goods.model\n" +
            "FROM goods\n" +
            "inner join goods_characteristic\n" +
            "on goods_id = goods_goods_id\n" +
            "inner join categories\n" +
            "on id_category= categories_id_category\n" +
            "where goods_id = ?";

    public static final String UPDATE_PRODUCT_QUERY = "UPDATE goods SET name = ?," +
            "count = ?, price = ?, status = ?, path = ?, model = ?\n" +
            "WHERE goods_id = ? ";

    public static final String UPDATE_CHARACTERISTICS_QUERY = "UPDATE goods_characteristic SET\n" +
            "value = ? " +
            "WHERE goods_goods_id = ? and characteristic = ?";

    public static final String ADD_PRODUCT_QUERY = "INSERT INTO " +
            "basket(goods_goods_id, user_id_user)\n" +
            "VALUES(?, ?)";

    public static final String DELETE_PRODUCT_QUERY = "DELETE FROM basket " +
            "WHERE goods_goods_id = ? AND user_id_user = ?";
    public static final String DELETE_ALL_PRODUCT_FROM_BASKET = "DELETE FROM basket " +
            "WHERE user_id_user = ?";

    public static final String GET_BASKET_PRODUCT = "SELECT * from store.basket\n" +
            "INNER JOIN store.goods\n" +
            "ON goods_id = goods_goods_id\n" +
            "INNER JOIN goods_characteristic\n" +
            "ON goods_characteristic.goods_goods_id = goods.goods_id\n" +
            "INNER JOIN store.categories\n" +
            "ON categories_id_category = id_category\n" +
            "INNER JOIN store.user \n" +
            "ON id_user =  ?";

    public static final String COUNT_BASKET_PRODUCTS = "SELECT COUNT(*) AS count FROM basket\n" +
            "where user_id_user = ?";

    public static final String CHECK_PRODUCT_QUERY = "SELECT EXISTS(SELECT id FROM basket WHERE " +
            "goods_goods_id = ? AND user_id_user = ?)";


    //orders
    public static final  String ADD_ORDER_QUERY = "INSERT INTO orders\n" +
            "(user_id_user, address, delivery_option, sum, status, date)\n" +
            "VALUES(?,?,?,?,?,?)";

    public static final  String ADD_ORDER_PRODUCT = "INSERT INTO goods_order" +
            "(orders_id_order, goods_goods_id)" +
            "VALUES(?,?)";

    public static final String GET_ALL_ORDERS = "select * from goods_order \n" +
            "inner join (\n" +
            "SELECT id_order, user_id_user , rownumber\n" +
            "from (SELECT id_order, user_id_user, row_number() over(ORDER BY id_order) AS RowNumber FROM store.orders) as k\n" +
            "where k.rownumber between ? and ?) as t\n" +
            "on goods_order.orders_id_order = t.id_order\n" +
            "inner join goods\n" +
            "on goods.goods_id = goods_order.goods_goods_id\n" +
            "inner join goods_characteristic\n" +
            "on goods.goods_id = goods_characteristic.goods_goods_id\n" +
            "inner join user\n" +
            "on user.id_user = t.user_id_user\n" +
            "inner join categories\n" +
            "on goods.categories_id_category = categories.id_category\n" +
            "inner join orders\n" +
            "on orders.id_order = t.id_order";

    public static final String COUNT_ALL_ORDERS = "select count(*) as count from orders";

    public static final String GET_ALL_USER_ORDERS = "SELECT * FROM store.orders\n" +
            "inner join goods_order\n" +
            "on id_order = orders_id_order\n" +
            "inner join goods\n" +
            "on goods.goods_id = goods_order.goods_goods_id\n" +
            "inner join goods_characteristic\n" +
            "on goods_id = goods_characteristic.goods_goods_id\n" +
            "inner join user\n" +
            "on id_user = orders.user_id_user\n" +
            "inner join categories\n" +
            "on goods.categories_id_category = categories.id_category\n" +
            "where user_id_user = ?";

    public static final String GET_ALL_ORDERS_BY_STATUS = "select * from goods_order \n" +
            "inner join (\n" +
            "SELECT id_order, user_id_user , rownumber\n" +
            "from (SELECT id_order, user_id_user, status, row_number() over(ORDER BY id_order) AS RowNumber FROM store.orders\n" +
            "where status = ?\n" +
            ") as k\n" +
            "where k.rownumber between ? and ?) as t\n" +
            "on goods_order.orders_id_order = t.id_order\n" +
            "inner join goods\n" +
            "on goods.goods_id = goods_order.goods_goods_id\n" +
            "inner join goods_characteristic\n" +
            "on goods.goods_id = goods_characteristic.goods_goods_id\n" +
            "inner join user\n" +
            "on user.id_user = t.user_id_user\n" +
            "inner join categories\n" +
            "on goods.categories_id_category = categories.id_category\n" +
            "inner join orders\n" +
            "on orders.id_order = t.id_order\n";

    public static final String GET_COUNT_ORDERS_BY_STATUS = "select count(*) as count from orders\n" +
            "where orders.status = ?";

    public static final String GET_ORDERS_BY_NAME = "select * from goods_order\n" +
            "inner join (\n" +
            "SELECT id_order, user_id_user , rownumber\n" +
            "from (SELECT id_order, user_id_user, username, surname, row_number() over(ORDER BY id_order) AS RowNumber \n" +
            "FROM store.orders\n" +
            "inner join user\n" +
            "on user.id_user = orders.user_id_user\n" +
            "where concat(username, ' ', surname) like ?\n" +
            ") as k\n" +
            "where k.rownumber between ? and ?) as t\n" +
            "on goods_order.orders_id_order = t.id_order\n" +
            "inner join goods\n" +
            "on goods.goods_id = goods_order.goods_goods_id\n" +
            "inner join goods_characteristic\n" +
            "on goods.goods_id = goods_characteristic.goods_goods_id\n" +
            "inner join user\n" +
            "on user.id_user = t.user_id_user\n" +
            "inner join categories\n" +
            "on goods.categories_id_category = categories.id_category\n" +
            "inner join orders\n" +
            "on orders.id_order = t.id_order";

    public static final String GET_COUNT_ORDERS_BY_NAME = "select count(*) as count from user\n" +
            "inner join orders on id_user = orders.user_id_user\n" +
            "where concat(username, ' ', surname) like ?";

    public static final String GET_ORDER_BY_ID = "SELECT * FROM store.orders\n" +
            "inner join goods_order\n" +
            "on id_order = orders_id_order\n" +
            "inner join goods\n" +
            "on goods.goods_id = goods_order.goods_goods_id\n" +
            "inner join goods_characteristic\n" +
            "on goods_id = goods_characteristic.goods_goods_id\n" +
            "inner join categories\n" +
            "on goods.categories_id_category = categories.id_category\n" +
            "inner join user\n" +
            "on id_user = orders.user_id_user\n" +
            "where id_order = ?";

    public static final String CHANGE_ORDER_STATUS= "UPDATE ORDERS SET status= ? where id_order = ?";
}

package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductFactory;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.good.EBook;
import by.tsarenkov.shop.bean.status.ProductStatus;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.dao.db.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class SQLProductDAO implements ProductDAO {

    private static final Logger LOGGER = Logger.getLogger(SQLProductDAO.class);


    private static final ConnectionPool POOL = ConnectionPool.getInstance();

    private static final String NAME = "name";
    private static final String[] GOODS_ID = new String[]{"good_id"};
    private static final String BRAND = "brand";
    private static final String CHARACTERISTIC = "characteristic";
    private static final String  GOOD_ID = "goods_id";
    private static final String VALUE = "value";
    private static final String COUNT = "count";
    private static final String PRICE = "price";
    private static final String STATUS = "status";
    private static final String PATH = "path";
    private static final String GOOD_ID_COLUMN = "goods_goods_id";
    private static final String NAME_CATEGORY = "name_category";

    private static final String SAVE_PRODUCT_QUERY = "INSERT INTO goods " +
            "(name, categories_id_category, count, price, status, path) VALUES(?,?,?,?,?,?)";

    private static final String SAVE_CHARACTERISTIC_QUERY = "INSERT INTO goods_characteristic" +
            "(goods_goods_id, characteristic, value) values(?,?,?)";

    private static final String GET_PRODUCT_QUERY = " SELECT goods_id, name, count, price, status, characteristic, value  \n" +
            " FROM store.goods\n" +
            " INNER JOIN store.GOODS_CHARACTERISTIC\n" +
            " ON GOODS_ID = GOODS_GOODS_ID\n" +
            " INNER JOIN store.categories\n" +
            " on categories_id_category = id_category\n" +
            " where name_category = ? \n" +
            "  ORDER BY goods_id";

    private static final String GET_PRODUCT_QUERY_BY_ID = "SELECT name, count, price, status, characteristic, value " +
            "FROM store.GOODS\n" +
            "INNER JOIN store.GOODS_CHARACTERISTIC\n" +
            "ON GOODS_ID = GOODS_GOODS_ID\n" +
            "WHERE goods_id = ?";

    private static final String UPDATE_PRODUCT_QUERY = "UPDATE goods SET name = ?," +
            "count = ?, price = ?, status = ?, path = ?" +
            "WHERE goods_id = ? ";
    private static final String UPDATE_CHARACTERISTICS_QUERY = "UPDATE goods_characteristic SET\n" +
            "value = ? " +
            "WHERE goods_goods_id = ? and characteristic = ?";

    private static final String ADD_PRODUCT_QUERY = "INSERT INTO BASKET (goods_goods_id, user_id_user, count)\n" +
            "VALUES(?, ?, ?)";
    private static final String DELETE_PRODUCT_QUERY = "DELETE FROM BASKET WHERE goods_goods_id = ? AND user_id_user = ?";
    private static final String GET_BASKET_PRODUCT = "SELECT goods_goods_id, name_category from store.basket\n" +
            "INNER JOIN store.goods\n" +
            "ON goods_id = goods_goods_id\n" +
            "INNER JOIN store.categories\n" +
            "ON categories_id_category = id_category\n" +
            "INNER JOIN store.user\n" +
            "ON id_user =  ?";

    private static final String CHECK_PRODUCT_QUERY = "SELECT EXISTS(SELECT id FROM BASKET WHERE " +
            "goods_goods_id = ? AND user_id_user = ?)";

    @Override
    public boolean addNewProduct(int idCategory, String brand,
                                 int count, double price,
                                 ProductStatus status, String path,
                                 Map<String, String> productCharacteristic) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(SAVE_PRODUCT_QUERY, GOODS_ID);
            preparedStatement.setString(1, brand);
            preparedStatement.setInt(2, idCategory);
            preparedStatement.setInt(3, count);
            preparedStatement.setDouble(4, price);
            preparedStatement.setString(5, status.toString());
            preparedStatement.setString(6, path);

            int result = preparedStatement.executeUpdate();
            int goodId = 0;

            if (result == 1) {
               ResultSet rs = preparedStatement.getGeneratedKeys();
               if (rs.next()) {
                   goodId = rs.getInt(1);
               }
               for (Map.Entry<String,String> entry : productCharacteristic.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    preparedStatement = connection.prepareStatement(SAVE_CHARACTERISTIC_QUERY);
                    preparedStatement.setInt(1, goodId);
                    preparedStatement.setString(2, key);
                    preparedStatement.setString(3, value);
                    preparedStatement.executeUpdate();
               }
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            POOL.closeConnection(connection, preparedStatement);
        }
        return false;
    }

    @Override
    public boolean changeProduct(int idProduct, String brand,
                                 int count, double price,
                                 ProductStatus status, String path,
                                 Map<String, String> productCharacteristic)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_QUERY);
            preparedStatement.setString(1, brand);
            preparedStatement.setInt(2, count);
            preparedStatement.setDouble(3, price);
            preparedStatement.setString(4, status.toString());
            preparedStatement.setString(5,path);
            preparedStatement.setInt(6, idProduct);
            preparedStatement.executeUpdate();
            for (Map.Entry<String,String> entry : productCharacteristic.entrySet()) {
                String key = entry.getKey();
                System.out.println(key);
                String value = entry.getValue();
                preparedStatement = connection.prepareStatement(UPDATE_CHARACTERISTICS_QUERY);
                preparedStatement.setString(1, value);
                preparedStatement.setInt(2, idProduct);
                preparedStatement.setString(3, key);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException();
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement);
        }
        return true;
    }


    @Override
    public List<Product> getAllProducts(ProductName type) throws DAOException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Map<String, String> characteristics = new HashMap<>();
        List<Product> products = new ArrayList<>();
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(GET_PRODUCT_QUERY);
            preparedStatement.setString(1, type.toString());
            resultSet = preparedStatement.executeQuery();
            int current_good = 0;
            String currentBrand = "";
            int count = 0;
            double price = 0;
            String characteristic = null;
            String value = null;
            ProductStatus status = ProductStatus.EXPECTED;
            while (resultSet.next()) {
                int id = resultSet.getInt(GOOD_ID);
                characteristic = resultSet.getString(CHARACTERISTIC);
                value = resultSet.getString(VALUE);
                if (current_good == 0) {
                    current_good = id;
                    count = resultSet.getInt(COUNT);
                    price = resultSet.getDouble(PRICE);
                    currentBrand = resultSet.getString(NAME);
                    status = ProductStatus.valueOf(resultSet.getString(STATUS));

                }
                if (current_good != id) {
                    Product product = ProductFactory
                            .getProduct(type, current_good,
                                    currentBrand, count, price,
                                    status,"path", characteristics);
                    products.add(product);
                    characteristics.clear();
                    current_good = id;
                    count = resultSet.getInt(COUNT);
                    price = resultSet.getDouble(PRICE);
                    currentBrand = resultSet.getString(NAME);
                    status = ProductStatus.valueOf(resultSet.getString(STATUS));
                }
                if (resultSet.isLast()) {
                    characteristics.put(characteristic.toUpperCase(), value);
                    characteristics.put(characteristic.toUpperCase(), value);
                    Product product = ProductFactory
                            .getProduct(type, current_good,
                                    currentBrand, count, price,
                                    status,"path", characteristics);
                    products.add(product);

                }
                characteristics.put(characteristic.toUpperCase(), value);
            }
        } catch (SQLException e) {
            throw new DAOException(); //msg & log
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
        return products;
    }

    @Override
    public List<Product> getProductByCharacteristics() throws DAOException {
        return null;
    }

    @Override
    public Product getProduct(ProductName name, int idProduct) throws DAOException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Map<String, String> characteristics = new HashMap<>();
        Product product = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(GET_PRODUCT_QUERY_BY_ID);
            preparedStatement.setInt(1, idProduct);
            resultSet = preparedStatement.executeQuery();
                String brand = "";
                int count = 0;
                double price = 0;
                String characteristic = null;
                String value = null;
                ProductStatus status = ProductStatus.EXPECTED;
            while (resultSet.next()) {
                characteristic = resultSet.getString(CHARACTERISTIC);
                value = resultSet.getString(VALUE);
                if (resultSet.isFirst()) {
                    count = resultSet.getInt(COUNT);
                    price = resultSet.getDouble(PRICE);
                    brand = resultSet.getString(NAME);
                    status = ProductStatus.valueOf(resultSet.getString(STATUS));
                }
                if (resultSet.isLast()) {
                    characteristic = resultSet.getString(CHARACTERISTIC);
                    characteristics.put(characteristic.toUpperCase(), value);
                    product = ProductFactory
                            .getProduct(name, idProduct,
                                    brand, count, price,
                                    status,"path", characteristics);
                }
                characteristics.put(characteristic.toUpperCase(), value);
            }
        } catch (SQLException e) {
            throw new DAOException();
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
        return product;
    }

    @Override
    public List<Product> getAllProductsFromBasket(int idUser)  throws DAOException {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(GET_BASKET_PRODUCT);
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                int idProduct = resultSet.getInt(GOOD_ID_COLUMN);
                ProductName name = ProductName.valueOf(resultSet.getString(NAME_CATEGORY));
                Product product = getProduct(name, idProduct);
                products.add(product);
            }
        } catch (SQLException e) {
            //TODO
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement);
        }
        return products;
    }

    @Override
    public boolean deleteProduct(int idProduct, int idUser)  throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(DELETE_PRODUCT_QUERY );
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setInt(2, idUser);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            //TODO
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement);
        }
        return true;
    }

    @Override
    public boolean addProduct(int idProduct, int idUser, int count) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(ADD_PRODUCT_QUERY);
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setInt(2, idUser);
            preparedStatement.setInt(3, count);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //TODO ADD LOG
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement);
        }
        return true;
    }

    @Override
    public boolean checkProduct(int idProduct, int idUser) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = POOL.takeConnection();
            preparedStatement = connection.prepareStatement(CHECK_PRODUCT_QUERY);
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setInt(2, idUser);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt(1) != 0;
        } catch (SQLException e) {
            //TODO ADD LOG
            throw new DAOException(e);
        } finally {
            POOL.returnConnectionToPool(connection, preparedStatement, resultSet);
        }
    }
}

package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.ProductFactory;
import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.good.EBook;
import by.tsarenkov.shop.dao.DAOException;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.dao.db.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class SQLProductDAO implements ProductDAO {

    private static final Logger log = Logger.getLogger(SQLProductDAO.class);
    private List<Product> products;

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String nameColumn = "name";
    private static final String[] goodId = new String[]{"good_id"};
    private static final String brand = "brand";
    private static final String characteristicColumn = "characteristic";
    private static final String  goodsIdColumn = "goods_id";
    private static final String valueCharacteristicColumn = "value";

    private static final String saveProductQuery = "INSERT INTO goods " +
            "(name, categories_id_category) VALUES(?,?)";

    private static final String saveCharacteristicQuery = "INSERT INTO goods_characteristic" +
            "(goods_goods_id, characteristic, value) values(?,?,?)";

    private static final String getProductQuery = "SELECT goods_id, name, characteristic, value FROM store.GOODS\n" +
            "INNER JOIN store.GOODS_CHARACTERISTIC\n" +
            "ON GOODS_ID = GOODS_GOODS_ID\n" +
            "WHERE CATEGORIES_ID_CATEGORY = 1 ORDER BY goods_id";

    @Override
    public boolean addNewProduct(Map<String, String> product) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(saveProductQuery, goodId);
            preparedStatement.setString(1, product.get(brand)); // change
            preparedStatement.setInt(2, EBook.ID_CATEGORY); // change
            int result = preparedStatement.executeUpdate();
            System.out.println(result);
            int goodId = 0;
            if (result == 1) {
               ResultSet rs = preparedStatement.getGeneratedKeys();
               if (rs.next()) {
                   goodId = rs.getInt(1);
               }
                System.out.println(goodId);
               for (Map.Entry<String,String> entry : product.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    preparedStatement = connection.prepareStatement(saveCharacteristicQuery);
                    preparedStatement.setInt(1, goodId);
                    preparedStatement.setString(2, key);
                    preparedStatement.setString(3, value);
                    preparedStatement.executeUpdate();
               }

            }

        } catch (SQLException e) {
            System.out.println(e);
            throw new DAOException();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.debug("Prepared statement wasn't closed");
            }
            try {
                connection.close();
            } catch (SQLException e) {
                log.debug("Connection wasn't closed");
            }
        }
        return false;
    }

    @Override
    public boolean changeProduct() throws DAOException {
        return false;
    }

    @Override
    public List<Product> getAllProducts() throws DAOException {
        ProductName type = ProductName.EBOOK; //change parameters
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Map<String, String> characteristics = new HashMap<>();
        products = new ArrayList<>();
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(getProductQuery);
            resultSet = preparedStatement.executeQuery();
            int current_good = 0;
            String currentBrand = "";
            while (resultSet.next()) {
                int id = resultSet.getInt(goodsIdColumn);
                if (current_good == 0) {
                    current_good = id;
                }
                if (current_good != id) {
                    characteristics.put(nameColumn.toUpperCase(), currentBrand);
                    Product product = ProductFactory.getProduct(type, current_good, characteristics);
                    products.add(product);
                    characteristics.clear();
                    current_good = id;
                }
                if (resultSet.isLast()) {
                    characteristics.put(nameColumn.toUpperCase(), currentBrand);
                    Product product = ProductFactory.getProduct(type, current_good, characteristics);
                    products.add(product);
                    return products;
                }
                String characteristic = resultSet.getString(characteristicColumn);
                String value = resultSet.getString(valueCharacteristicColumn);
                currentBrand = resultSet.getString(nameColumn);
                characteristics.put(characteristic.toUpperCase(), value);
            }
        } catch (SQLException e) {
            throw new DAOException(); //msg & log
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                log.debug("Result set wasn't closed");
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.debug("Prepared statement wasn't closed");
            }
            try {
                connection.close();
            } catch (SQLException e) {
                log.debug("Connection wasn't closed");
            }
        }
        return null;
    }

    @Override
    public List<Product> getProductByCharacteristics() throws DAOException {
        return null;
    }
}

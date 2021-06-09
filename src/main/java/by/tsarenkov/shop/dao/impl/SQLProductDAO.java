package by.tsarenkov.shop.dao.impl;

import by.tsarenkov.shop.bean.good.EBook;
import by.tsarenkov.shop.dao.ProductDAO;
import by.tsarenkov.shop.dao.db.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLProductDAO implements ProductDAO {

    public static final ConnectionPool pool = ConnectionPool.getInstance();

    public static final String tabletInsertionQuery = "INSERT INTO goods " +
            "(name, categories_id_category) VALUES(?,?)";

    public SQLProductDAO() {

    }


    @Override
    public boolean addNewTablet() {

        return false;
    }

    @Override
    public boolean addNewEBook(EBook eBook) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(tabletInsertionQuery);
            preparedStatement.setString(1, eBook.getBrand());
            preparedStatement.setInt(2, EBook.getIdCategory());
            preparedStatement.executeUpdate();
            // add characteristic ?

        } catch (SQLException e) {
            //**
        }
        return false;
    }

    @Override
    public boolean addNewLaptop() {
        return false;
    }

    @Override
    public boolean addNewSmartPhone() {
        return false;
    }
}

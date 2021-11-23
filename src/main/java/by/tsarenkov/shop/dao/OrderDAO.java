package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.Order;
import by.tsarenkov.shop.bean.status.StatusOrder;
import java.util.List;

/**
 * Interface describes the opportunity to add
 * and edit {@link Order} into data source
 * **/

public interface OrderDAO {

    /**
     * Adds the {@link Order} into data source
     *
     * @param order contains order's data
     * @return true if order is added
     * @throws DAOException if an error occurs while adding data of the products
     * **/
    boolean addNewOrder(Order order) throws DAOException;

    /**
     * Gets list of {@link Order} from data source
     *
     * @param start contains start index for pagination
     * @param end contains end index for pagination
     * @return list {@link Order}
     * @throws DAOException if an error occurs while getting data of the products
     * **/

    List<Order> getAllOrders(int start, int end)
            throws DAOException;

    /**
     * Gets count of {@link Order} from data source
     * @return count {@link Order}
     * @throws DAOException if an error occurs while counting data of the products
     * **/
    int getCountAllOrders()
            throws DAOException;

    /**
     * Gets list of {@link Order} from data source by name
     *
     * @param name contains name for searching Order into data source
     * @param start contains start index for pagination
     * @param end contains end index for pagination
     * @return list {@link Order}
     * @throws DAOException if an error occurs while getting data of the products
     * **/
    List<Order> getOrdersByName(String name, int start, int end) throws DAOException;

    /**
     * Gets count of {@link Order} from data source by name
     * @return count {@link Order}
     * @throws DAOException if an error occurs while counting data of the products
     * **/
    int getCountOrderByName(String name) throws DAOException;

    /**
     * Gets list of {@link Order} from data source by user
     *
     * @param idUser contains user's id for searching Order into data source
     * @param start contains start index for pagination
     * @param end contains end index for pagination
     * @return list {@link Order}
     * @throws DAOException if an error occurs while getting data of the products
     * **/
    List<Order> getAllUserOrders(int idUser, int start, int end)
            throws DAOException;

    /**
     * Gets list of {@link Order} from data source by status
     *
     * @param statusOrder contains user's id for searching Order into data source by status
     * @param start contains start index for pagination
     * @param end contains end index for pagination
     * @return list {@link Order}
     * @throws DAOException if an error occurs while getting data of the products
     * **/
    List<Order> getAllOrdersByStatus(StatusOrder statusOrder, int start, int end)
            throws DAOException;

    /**
     * Gets count of {@link Order} from data source by status
     * @return count {@link Order}
     * @throws DAOException if an error occurs while counting data of the products
     * **/
    int getCountAllOrdersByStatus(StatusOrder statusOrder)
            throws DAOException;


    /**
     * Change {@link Order}'s status into data source
     *
     * @param idOrder contains order's id
     * @param status containg a new status for changing order's status
     * @return true if status is changed
     * @throws DAOException if an error occurs while changing data of the products
     * **/
    boolean changeOrderStatus(int idOrder, StatusOrder status)
            throws DAOException;

    /**
     * Gets {@link Order} from data source by id
     *
     * @param idOrder contains order's id
     * @return {@link Order}
     * @throws DAOException if an error occurs while getting data of the products
     * **/
    Order getOrder(int idOrder) throws DAOException;
}

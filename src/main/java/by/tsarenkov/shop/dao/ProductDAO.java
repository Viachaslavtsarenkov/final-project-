package by.tsarenkov.shop.dao;

import by.tsarenkov.shop.bean.good.EBook;

public interface ProductDAO {

    boolean addNewTablet();
    boolean addNewEBook(EBook eBook);
    boolean addNewLaptop();
    boolean addNewSmartPhone();

}

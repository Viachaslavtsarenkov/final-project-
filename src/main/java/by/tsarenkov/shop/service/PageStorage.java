package by.tsarenkov.shop.service;

public enum PageStorage {

    PRODUCTS_PAGE_PATH("/WEB-INF/jsp/view/products_page.jsp"),
    ERROR_PAGE_PATH("/WEB-INF/jsp/error.jsp"),
    MAIN_PAGE_PATH("/WEB-INF/jsp/main.jsp"),
    USERS_PAGE_PATH("/WEB-INF/jsp/view/user_view.jsp"),
    LOGIN_PAGE_PATH("/WEB-INF/jsp/login.jsp"),
    REGISTRATION_PAGE_PATH("/WEB-INF/jsp/registration.jsp"),
    BASKET_PAGE_PATH("/WEB-INF/jsp/basket/basket.jsp"),
    ORDER_PAGE_PATH("/WEB-INF/jsp/input_page/order_input.jsp"),
    PERSONAL_PAGE_PATH("/WEB-INF/jsp/personal_page.jsp"),
    INDEX_PAGE_PATH("index.jsp"),
    ORDERS_PAGE_PATH("/WEB-INF/jsp/view/orders_page.jsp"),
    PARTICULAR_ORDER_PAGE_PATH("/WEB-INF/jsp/view/particular_order_view.jsp"),
    PARTICULAR_PRODUCT_PAGE_PATH("/WEB-INF/jsp/view/particular_productView.jsp"),
    EBOOK_INPUT_PAGE_PATH("/WEB-INF/jsp/input_page/ebook_input_page.jsp"),
    LAPTOP_INPUT_PAGE_PATH("/WEB-INF/jsp/input_page/laptop_input_page.jsp"),
    TABLET_INPUT_PAGE_PATH("/WEB-INF/jsp/input_page/tablet_input_page.jsp"),
    SMARTPHONE_INPUT_PAGE_PATH("/WEB-INF/jsp/input_page/smartphone_input_page.jsp");

    private  String path;

    PageStorage(String path) {
        this.path = path;
    }

    public String getPATH() {
        return path;
    }
}

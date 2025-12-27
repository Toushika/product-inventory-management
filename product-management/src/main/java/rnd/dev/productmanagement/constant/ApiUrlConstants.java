package rnd.dev.productmanagement.constant;

public class ApiUrlConstants {

    private ApiUrlConstants() {

    }

    public static final String INDEX_URL = "/index";
    public static final String PRODUCT_BASE_URL = "/product";
    public static final String SAVE_URL = "/save";
    public static final String GET_ALL_URL = "/get/all";
    public static final String GET_BY_ID = "/id";
    public static final String GET_BY_CATEGORY = "/category";
    public static final String UPDATE_BY_ID_URL = "/update/{productId}";
    public static final String DELETE_BY_ID_URL = "/delete/{productId}";
    public static final String DELETE_ALL = "/delete/all";
    public static final String AVAILABILITY_CHECK_URL = "/availability/{productId}";
}

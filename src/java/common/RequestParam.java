package common;

/**
 *
 * @author PC
 */
public class RequestParam {
    public static final String PAGE = "page";
    
    public static final String OFFSET = "offset";
    public static final String LENGTH = "length";
    
    // search
    public static final String KEYWORD = "keyword";
    public static final String CATEGORY_ID = "category_id";
    
    // add to cart
    public static final String CAR_ID = "car_id";
    public static final String QUANTITY = "quantity";
    
    public static class UserParam {
        public static final String EMAIL = "email";
        public static final String PHONE = "phone";
        public static final String NAME = "name";
        public static final String ADDRESS = "address";
        public static final String PASSWORD = "password";
        
        public static final String ROLE_ID = "role_id";
        public static final String ROLE_NAME = "role_name";
        
        public static final String STATUS_ID = "status_id";
        public static final String STATUS_NAME = "status_name";
        
        public static final String CREATE_DATE = "create_date";
    }
    
    public static class CarParam {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String IMAGE = "image";
        public static final String COLOR = "color";
        public static final String YEAR = "year";
        public static final String PRICE = "price";
        public static final String QUANTITY = "quantity";
        public static final String BRANCH = "branch";
        public static final String CATEGORY_ID = "category_id";
        public static final String CATEGORY_NAME = "category_name";
        public static final String RATING = "rating";
    }
    
    public static class CategoryParam {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
    }
}

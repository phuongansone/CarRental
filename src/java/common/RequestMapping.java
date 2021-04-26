package common;

/**
 *
 * @author PC
 */
public class RequestMapping {
    public static class RegisterRequest {
        public static final String ACTION = "register";
        public static final String SERVLET = "RegisterServlet";
        public static final String VIEW = "/WEB-INF/pages/register.jsp";
        private RegisterRequest() {
        }
    }
    
    public static class RegisterSuccessRequest {
        public static final String ACTION = "registerSuccess";
        public static final String SERVLET = "RegisterSuccessServlet";
        public static final String VIEW = "/WEB-INF/pages/register-success.jsp";
        private RegisterSuccessRequest() {
        }
    }
    
    public static class LoginRequest {
        public static final String ACTION = "login";
        public static final String SERVLET = "LoginServlet";
        public static final String VIEW = "/WEB-INF/pages/login.jsp";
        private LoginRequest() {
        }
    }
    
    public static class LogoutRequest {
        public static final String ACTION = "logout";
        public static final String SERVLET = "LogoutServlet";
        private LogoutRequest() {
        }
    }
    
    public static class SearchCarRequest {
        public static final String ACTION = "searchCar";
        public static final String SERVLET = "SearchCarServlet";
        public static final String VIEW = "/WEB-INF/pages/search-car.jsp";
        private SearchCarRequest() {
        }
    }
    
    public static class AddToCartRequest {
        public static final String ACTION = "addToCart";
        public static final String SERVLET = "AddToCartServlet";
        private AddToCartRequest() {
        }
    }
    
    public static class ViewCartRequest {
        public static final String ACTION = "viewCart";
        public static final String SERVLET = "ViewCartServlet";
        public static final String VIEW = "/WEB-INF/pages/view-cart.jsp";
        private ViewCartRequest() {
        }
    }
    
    public static class UpdateCartRequest {
        public static final String ACTION = "updateCart";
        public static final String SERVLET = "UpdateCartServlet";
        private UpdateCartRequest() {
        }
    }
    
    public static class RemoveFromCartRequest {
        public static final String ACTION = "removeFromCart";
        public static final String SERVLET = "RemoveFromCartServlet";
        private RemoveFromCartRequest() {
        }
    }
    
    public static class SaveOrderRequest {
        public static final String ACTION = "saveOrder";
        public static final String SERVLET = "SaveOrderServlet";
        public static final String VIEW_GET = "/WEB-INF/pages/save-order-form.jsp";
        private SaveOrderRequest() {
        }
    }
    
    public static class AddDiscountRequest {
        public static final String ACTION = "addDiscount";
        public static final String SERVLET = "AddDiscountServlet";
        private AddDiscountRequest() {
        }
    }
    
    public static class OrderHistoryRequest {
        public static final String ACTION = "orderHistory";
        public static final String SERVLET = "OrderHistoryServlet";
        public static final String VIEW = "/WEB-INF/pages/history.jsp";
        private OrderHistoryRequest() {
        }
    }
    
    public static class OrderRequest {
        public static final String ACTION = "order";
        public static final String SERVLET = "OrderServlet";
        public static final String VIEW = "/WEB-INF/pages/order.jsp";
        private OrderRequest() {
        }
    }
    
    public static class CancelOrderRequest {
        public static final String ACTION = "cancelOrder";
        private CancelOrderRequest() {
        }
    }
    
    public static class UpdateRatingRequest {
        public static final String ACTION = "updateRating";
        private UpdateRatingRequest() {
        }
    }
}

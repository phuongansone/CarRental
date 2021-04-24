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
}

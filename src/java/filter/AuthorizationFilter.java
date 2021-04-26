package filter;

import common.CommonAttribute;
import common.RequestMapping.AddDiscountRequest;
import common.RequestMapping.AddToCartRequest;
import common.RequestMapping.CancelOrderRequest;
import common.RequestMapping.LogoutRequest;
import common.RequestMapping.OrderHistoryRequest;
import common.RequestMapping.OrderRequest;
import common.RequestMapping.RemoveFromCartRequest;
import common.RequestMapping.SaveOrderRequest;
import common.RequestMapping.SearchCarRequest;
import common.RequestMapping.UpdateCartRequest;
import common.RequestMapping.UpdateRatingRequest;
import common.RequestMapping.ViewCartRequest;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.RequestUtil;

/**
 *
 * @author PC
 */
public class AuthorizationFilter implements Filter {
    
    private static final boolean debug = true;
    
    private static final List<String> ADMIN_PERMISSION = new ArrayList<>();
    
    private static final List<String> USER_PERMISSION = new ArrayList<>();

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AuthorizationFilter() {
        ADMIN_PERMISSION.add(LogoutRequest.ACTION);
        ADMIN_PERMISSION.add(SearchCarRequest.ACTION);
        ADMIN_PERMISSION.add("");
        
        USER_PERMISSION.add(LogoutRequest.ACTION);
        USER_PERMISSION.add(SearchCarRequest.ACTION);
        USER_PERMISSION.add(AddToCartRequest.ACTION);
        USER_PERMISSION.add(ViewCartRequest.ACTION);
        USER_PERMISSION.add(UpdateCartRequest.ACTION);
        USER_PERMISSION.add(RemoveFromCartRequest.ACTION);
        USER_PERMISSION.add(SaveOrderRequest.ACTION);
        USER_PERMISSION.add(AddDiscountRequest.ACTION);
        USER_PERMISSION.add(OrderHistoryRequest.ACTION);
        USER_PERMISSION.add(OrderRequest.ACTION);
        USER_PERMISSION.add(CancelOrderRequest.ACTION);
        USER_PERMISSION.add(UpdateRatingRequest.ACTION);
        USER_PERMISSION.add("");
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizationFilter:DoBeforeProcessing");
        }
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizationFilter:DoAfterProcessing");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) {
            log("AuthorizationFilter:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try {
            HttpServletResponse res = (HttpServletResponse) response;
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession();
            
            UserDTO user = (UserDTO) session.getAttribute(CommonAttribute.USER);
            
            boolean authorized = true;
            
            String resource = RequestUtil.getRequestedResource(request);
            
            if (resource.endsWith(".css") 
                    || resource.endsWith(".js") 
                    || resource.endsWith(".map")
                    || resource.endsWith(".svg")) {
                chain.doFilter(request, response);
                return;
            }
            
            if (user.isAdmin() && !ADMIN_PERMISSION.contains(resource)) {
                authorized = false;
            }
            
            if (user.isUser()&& !USER_PERMISSION.contains(resource)) {
                authorized = false;
            }
            
            // if user is not authorized, send NOT FOUND error
            if (!authorized) {
                res.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            
            // if user is authorized, continue
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AuthorizationFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthorizationFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthorizationFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}

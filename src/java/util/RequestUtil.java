/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PC
 */
public class RequestUtil {
    private static final String SERVLET = "Servlet";
    
    /**
     * Get requested resource from request object
     * @param request
     * @return resource string
     */
    public static String getRequestedResource(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        
        int lastIndex = uri.lastIndexOf("/");
        return uri.substring(lastIndex + 1);
    }
    
    public static String getProcessingServlet(String resource) {
        if (resource == null || resource.length() == 0) {
            return "";
        }
        
        return resource.substring(0, 1).toUpperCase() 
                + resource.substring(1) + SERVLET;
    }
}

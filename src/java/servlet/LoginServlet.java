package servlet;

import common.CommonAttribute;
import common.RequestMapping.LoginRequest;
import common.RequestMapping.SearchCarRequest;
import dto.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import recaptcha.VerifyUtil;
import service.UserService;

/**
 *
 * @author PC
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.getRequestDispatcher(LoginRequest.VIEW).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        UserService userService = new UserService();
        UserDTO user;
        
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        if (!VerifyUtil.verify(gRecaptchaResponse)) {
            request.getSession().setAttribute(CommonAttribute.CAPTCHA_VALID, Boolean.FALSE);
            request.getRequestDispatcher(LoginRequest.VIEW).forward(request, response);
            return;
        }
        
        try {
            user = userService.checkUserCredential(request);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        HttpSession session = request.getSession();
        
        // if user email and password is valid
        if (user != null) {
            session.setAttribute(CommonAttribute.USER, user);
            session.setAttribute(CommonAttribute.LOGGED_IN, Boolean.TRUE);
            
            response.sendRedirect(SearchCarRequest.ACTION);
            return;
        }
        
        // if not, show error message
        session.setAttribute(CommonAttribute.LOGGED_IN, Boolean.FALSE);
        request.getRequestDispatcher(LoginRequest.VIEW).forward(request, response);
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

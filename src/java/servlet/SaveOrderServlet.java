package servlet;

import common.CommonAttribute;
import common.RequestMapping.SaveOrderRequest;
import common.RequestMapping.SearchCarRequest;
import common.RequestMapping.ViewCartRequest;
import dto.OrderDetailDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.OrderService;

/**
 *
 * @author PC
 */
public class SaveOrderServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        List<OrderDetailDTO> cart = (List<OrderDetailDTO>) session.getAttribute(CommonAttribute.CART);
        
        if (cart == null || checkCartHasOutOfStockItem(cart)) {
            response.sendRedirect(ViewCartRequest.ACTION);
            return;
        }
        
        request.getRequestDispatcher(SaveOrderRequest.VIEW_GET)
                .forward(request, response);
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
        
        HttpSession session = request.getSession();
        
        OrderService orderService = new OrderService();
        
        try {
            orderService.saveOrder(request);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SaveOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        session.removeAttribute(CommonAttribute.CART);
        session.removeAttribute(CommonAttribute.TOTAL_PRICE);
        session.removeAttribute(CommonAttribute.DISCOUNT);
        session.setAttribute(CommonAttribute.SAVE_ORDER, Boolean.TRUE);
        
        response.sendRedirect(SearchCarRequest.ACTION);
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
    
    private boolean checkCartHasOutOfStockItem(List<OrderDetailDTO> cart) {
        if (cart != null) {
            for (OrderDetailDTO orderDetail : cart) {
                if (orderDetail.isOutOfStock()) {
                    return true;
                }
            }            
        }
        
        return false;
    }
}

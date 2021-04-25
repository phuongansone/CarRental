package servlet;

import common.CommonAttribute;
import static common.CommonAttribute.ERROR;
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
import service.OrderDetailService;

/**
 *
 * @author PC
 */
public class ViewCartServlet extends HttpServlet {

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
        int totalPrice = 0;
        
        OrderDetailService orderDetailService = new OrderDetailService();
        
        try {
            orderDetailService.updateOutOfStockStatus(cart);
            totalPrice = OrderDetailService.calculateTotalPrice(cart);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ViewCartServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
            session.setAttribute(ERROR, Boolean.TRUE);
        }
        
        session.setAttribute(CommonAttribute.CART, cart);
        session.setAttribute(CommonAttribute.TOTAL_PRICE, totalPrice);
        
        request.getRequestDispatcher(ViewCartRequest.VIEW).forward(request, response);
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

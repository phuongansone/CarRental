package servlet;

import common.CommonAttribute;
import common.RequestMapping.SaveOrderRequest;
import common.RequestParam.DiscountParam;
import dto.DiscountDTO;
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
import service.DiscountService;
import service.OrderDetailService;

/**
 *
 * @author PC
 */
public class AddDiscountServlet extends HttpServlet {

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
        
        DiscountService discountService = new DiscountService();
        String code = request.getParameter(DiscountParam.CODE);
        DiscountDTO discount = null;
        
        List<OrderDetailDTO> cart = (List<OrderDetailDTO>) session.getAttribute(CommonAttribute.CART);
        long price = OrderDetailService.calculateTotalPrice(cart);
        
        
        try {
            discount = discountService.getDiscountByCode(code);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AddDiscountServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
            session.setAttribute(CommonAttribute.ERROR, Boolean.TRUE);
        }
        
        if (discount == null) {
            session.setAttribute(CommonAttribute.VALID, Boolean.FALSE);
        } else {
            if (!discountService.checkDiscountIsEffective(discount)) {
                // check if discount is effective
                session.setAttribute(CommonAttribute.EFFECTIVE, Boolean.FALSE);
            } else if (!discountService.checkDiscountIsNotExpired(discount)) {
                // check if discount is not expired
                session.setAttribute(CommonAttribute.NOT_EXPIRED, Boolean.FALSE);
            } else if (!discountService.checkDiscountAvalaibility(discount)) {
                // check if discount is not out of stock
                session.setAttribute(CommonAttribute.AVALAIBLE, Boolean.FALSE);
            } else {
                // re-calculate price
                price = Math.round(price * (1 - discount.getDiscount()));
                
                // set discount to session
                session.setAttribute(CommonAttribute.DISCOUNT, discount);
            }
        }
        
        session.setAttribute(CommonAttribute.TOTAL_PRICE, price);
        response.sendRedirect(SaveOrderRequest.ACTION);
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

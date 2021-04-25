package servlet;

import common.CommonAttribute;
import static common.CommonAttribute.ERROR;
import common.RequestMapping.OrderHistoryRequest;
import common.RequestParam;
import dto.OrderDTO;
import dto.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class OrderHistoryServlet extends HttpServlet {

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
        UserDTO user = (UserDTO) session.getAttribute(CommonAttribute.USER);
        
        OrderService orderService = new OrderService();
        
        List<OrderDTO> orders = new ArrayList<>();
        
        String keyword = request.getParameter(RequestParam.KEYWORD);
        String date = request.getParameter(RequestParam.DATE);
        
        try {
            if (keyword != null && !keyword.isBlank()) {
                orders = orderService.getOrdersByEmailAndName(user.getEmail(), keyword);
            } else if (date != null && !date.isBlank()) {
                orders = orderService.getOrdersByDate(user.getEmail(), date);
            } else {
                orders = orderService.getOrdersByEmail(user.getEmail());
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderHistoryServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
            session.setAttribute(ERROR, Boolean.TRUE);
        }
        
        request.setAttribute(CommonAttribute.ORDERS, orders);
        request.getRequestDispatcher(OrderHistoryRequest.VIEW).forward(request, response);
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

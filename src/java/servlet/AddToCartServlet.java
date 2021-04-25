package servlet;

import common.CommonAttribute;
import common.RequestMapping.SearchCarRequest;
import common.RequestParam;
import dto.OrderDetailDTO;
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
import service.CarService;
import util.StringUtil;

/**
 *
 * @author PC
 */
public class AddToCartServlet extends HttpServlet {

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
        
        // Get cart from session
        List<OrderDetailDTO> cart = (List<OrderDetailDTO>) session.getAttribute(CommonAttribute.CART);
        if (cart == null) {
            cart = new ArrayList<>();
        }
        
        int carId = StringUtil.parseInt(request.getParameter(RequestParam.CAR_ID), -1);
        int quantity = StringUtil.parseInt(request.getParameter(RequestParam.QUANTITY), 1);
        int index = CarService.searchCarInCart(cart, carId);
        boolean added = true;
        
        CarService carService = new CarService();
        if (index < 0) {
            // if cart does not contain food yet
            try {
                cart.add(new OrderDetailDTO(carService.getCarById(carId), quantity));
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(AddToCartServlet.class.getName())
                        .log(Level.SEVERE, null, ex);
                added = false;
            }
        } else {
            int currentQuantityInCart = cart.get(index).getQuantity();
            cart.get(index).setQuantity(currentQuantityInCart + quantity);
        }
        
        session.setAttribute(CommonAttribute.CART, cart);
        session.setAttribute(CommonAttribute.ADD_TO_CART, added);
        
        request.getRequestDispatcher(SearchCarRequest.SERVLET)
                .forward(request, response);
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

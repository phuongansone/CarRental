package service;

import common.CommonAttribute;
import common.RequestParam.OrderParam;
import dao.CarDAO;
import dao.DiscountDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dto.CarDTO;
import dto.DiscountDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import dto.UserDTO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
public class OrderService {
    private final OrderDAO orderDAO;
    private final OrderDetailDAO orderDetailDAO;
    private final CarDAO carDAO;
    private final DiscountDAO discountDAO;

    public OrderService() {
        orderDAO = new OrderDAO();
        orderDetailDAO = new OrderDetailDAO();
        carDAO = new CarDAO();
        discountDAO = new DiscountDAO();
    }
    
    public void saveOrder(HttpServletRequest request) 
            throws ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        List<OrderDetailDTO> cart = (List<OrderDetailDTO>) session.getAttribute(CommonAttribute.CART);
        
        // insert order
        int orderId = insertOrder(request);
        
        // insert detail
        for (OrderDetailDTO item : cart) {
            orderDetailDAO.insertOrderDetail(item, orderId);
            
            CarDTO car = item.getCar();
            carDAO.updateCarQuantity(car.getId(), car.getQuantity() - item.getQuantity());
        }
        
        // update discount quantity
        DiscountDTO discount = (DiscountDTO)session.getAttribute(CommonAttribute.DISCOUNT);
        if (discount != null) {
            discountDAO.updateDiscountById(discount.getId(), discount.getQuantity() - 1);
        }
    }
    
    public int insertOrder(HttpServletRequest request) 
            throws ClassNotFoundException, SQLException {
        OrderDTO order = mapRequestToOrderDTO(request);
        
        if (order.getDiscount() == null) {
            return orderDAO.insertWithoutDiscount(order);
        }
        
        return orderDAO.insertWithDiscount(order);
    }
    
    public List<OrderDTO> getOrdersByEmail(String email) 
            throws SQLException, ClassNotFoundException {
        return orderDAO.getOrdersByEmail(email);
    }
    
    public List<OrderDTO> getOrdersByEmailAndName(String email, String keyword) 
            throws SQLException, ClassNotFoundException {
        return orderDAO.getOrdersByEmailAndName(email, keyword);
    }
    
    public List<OrderDTO> getOrdersByDate(String email, String date) 
            throws SQLException, ClassNotFoundException {
        String startTime = date + " 00:00:00";
        String endTime = date + " 23:59:59";
        return orderDAO.getOrdersByDate(email, startTime, endTime);
    }
    
    public OrderDTO getOrderById(int orderId) 
            throws SQLException, ClassNotFoundException {
        OrderDTO order = orderDAO.getOrderById(orderId);
        order.setOrderDetails(orderDetailDAO.getOrderDetails(orderId));
        
        return order;
    }
    
    public boolean inactivateOrder(int orderId) 
            throws SQLException, ClassNotFoundException {
        return orderDAO.updateOrderStatus(orderId, Boolean.FALSE);
    }
    
    private OrderDTO mapRequestToOrderDTO(HttpServletRequest request) {
        OrderDTO order = new OrderDTO();
        
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO)session.getAttribute(CommonAttribute.USER);
        long price = (Long)session.getAttribute(CommonAttribute.TOTAL_PRICE);
        
        order.setDiscount((DiscountDTO)session.getAttribute(CommonAttribute.DISCOUNT));
        order.setEmail(user.getEmail());
        
        order.setFullname(request.getParameter(OrderParam.FULLNAME));
        order.setAddress(request.getParameter(OrderParam.ADDRESS));
        order.setPhone(request.getParameter(OrderParam.PHONE));
        order.setRentalDate(Date.valueOf(request.getParameter(OrderParam.RENTAL_DATE)));
        order.setReturnDate(Date.valueOf(request.getParameter(OrderParam.RETURN_DATE)));
        order.setPrice((int)price);
        
        return order;
    }
}

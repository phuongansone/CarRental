package service;

import dao.CarDAO;
import dao.OrderDetailDAO;
import dto.CarDTO;
import dto.OrderDetailDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PC
 */
public class OrderDetailService {

    private final CarDAO carDAO;
    private final OrderDetailDAO orderDetailDAO;

    public OrderDetailService() {
        carDAO = new CarDAO();
        orderDetailDAO = new OrderDetailDAO();
    }

    public void updateOutOfStockStatus(List<OrderDetailDTO> cart)
            throws SQLException, ClassNotFoundException {
        if (cart == null) {
            return;
        }

        for (OrderDetailDTO orderDetail : cart) {
            // get latest car information
            int carId = orderDetail.getCar().getId();
            CarDTO car = carDAO.getCarById(carId);
            orderDetail.setCar(car);
            
            orderDetail.setOutOfStock(car.getQuantity() < orderDetail.getQuantity());
        }
    }
    
    public boolean updateRating(int id, double rating) 
            throws SQLException, ClassNotFoundException {
        return orderDetailDAO.updateRating(id, rating);
    }
    
    public static int calculateTotalPrice(List<OrderDetailDTO> cart) {
        int total = 0;
        int itemPrice;
        
        if (cart != null) {
            for (OrderDetailDTO orderDetail : cart) {
                itemPrice = orderDetail.getQuantity() * orderDetail.getCar().getPrice();
                
                orderDetail.setPrice(itemPrice);
                total += itemPrice;
            }
        }
        
        return total;
    }
}

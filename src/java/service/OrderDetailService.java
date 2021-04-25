package service;

import dao.CarDAO;
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

    public OrderDetailService() {
        carDAO = new CarDAO();
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

package dao;

import common.RequestParam;
import common.RequestParam.CarParam;
import static common.RequestParam.QUANTITY;
import dto.CarDTO;
import dto.OrderDetailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseUtil;

/**
 *
 * @author PC
 */
public class OrderDetailDAO {
    private static final String INSERT_ORDER_DETAIL = "INSERT INTO order_detail "
            + "(order_id, car_id, quantity) "
            + "VALUES (?, ?, ?)";
    
    private static final String GET_ORDER_DETAILS = "SELECT c.name, c.image, c.color, "
            + "c.year, c.price, c.branch, od.quantity "
            + "FROM order_detail od "
            + "INNER JOIN car c ON od.car_id = c.id "
            + "WHERE order_id = ?";
    
    public int insertOrderDetail(OrderDetailDTO orderDetail, int orderId) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int insertedId = -1;
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(INSERT_ORDER_DETAIL, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, orderId);
                ps.setInt(2, orderDetail.getCar().getId());
                ps.setInt(3, orderDetail.getQuantity());
                
                if (ps.executeUpdate() > 0) {
                    rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        insertedId = rs.getInt(1);
                    }
                }
            }
            
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return insertedId;
    }
    
    public List<OrderDetailDTO> getOrderDetails(int orderId) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<OrderDetailDTO> orderDetails = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(GET_ORDER_DETAILS);
                ps.setInt(1, orderId);
                
                rs = ps.executeQuery();
                while (rs.next()) {
                    orderDetails.add(mapResultSetToOrderDetail(rs));
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return orderDetails;
    }
    
    private OrderDetailDTO mapResultSetToOrderDetail(ResultSet rs) throws SQLException {
        OrderDetailDTO orderDetail = new OrderDetailDTO();
        
        CarDTO car = new CarDTO();
        car.setName(rs.getString(CarParam.NAME));
        car.setImage(rs.getString(CarParam.IMAGE));
        car.setColor(rs.getString(CarParam.COLOR));
        car.setYear(rs.getInt(CarParam.YEAR));
        car.setPrice(rs.getInt(CarParam.PRICE));
        car.setBranch(rs.getString(CarParam.BRANCH));
        orderDetail.setCar(car);
        
        orderDetail.setQuantity(rs.getInt(QUANTITY));
        
        return orderDetail;
    }
}

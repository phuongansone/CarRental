package dao;

import common.RequestParam.OrderParam;
import dto.DiscountDTO;
import dto.OrderDTO;
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
public class OrderDAO {
    private static final String INSERT_WITHOUT_DISCOUNT = "INSERT INTO car_rental.order "
            + "(email, fullname, address, phone, rental_date, return_date, price, status) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
    
    private static final String INSERT_WITH_DISCOUNT = "INSERT INTO car_rental.order "
            + "(email, fullname, address, phone, rental_date, return_date, discount_id, price, status) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";
    
    private static final String GET_ORDERS_BY_EMAIL = "SELECT o.id, email, fullname, address, phone, "
            + "rental_date, return_date, price, "
            + "discount_id, d.discount, create_date "
            + "FROM car_rental.order o "
            + "LEFT JOIN discount d "
            + "ON o.discount_id = d.id "
            + "WHERE email = ? "
            + "ORDER BY create_date DESC";
    
    private static final String GET_ORDERS_BY_EMAIL_NAME = "SELECT o.id, email, fullname, address, phone, "
            + "rental_date, return_date, price, "
            + "discount_id, d.discount, create_date "
            + "FROM car_rental.order o "
            + "LEFT JOIN discount d "
            + "ON o.discount_id = d.id "
            + "WHERE email = ? AND fullname LIKE ? "
            + "ORDER BY create_date DESC";
    
    private static final String GET_ORDERS_BY_DATE = "SELECT o.id, email, fullname, address, phone, "
            + "rental_date, return_date, price, "
            + "discount_id, d.discount, create_date "
            + "FROM car_rental.order o "
            + "LEFT JOIN discount d "
            + "ON o.discount_id = d.id "
            + "WHERE email = ? "
            + "AND create_date BETWEEN ? AND ? "
            + "ORDER BY create_date DESC";
    
    private static final String GET_ORDER_BY_ID = "SELECT o.id, email, fullname, address, phone, "
            + "rental_date, return_date, price, "
            + "discount_id, d.discount, create_date "
            + "FROM car_rental.order o "
            + "LEFT JOIN discount d "
            + "ON o.discount_id = d.id "
            + "WHERE o.id = ?";
    
    public int insertWithDiscount(OrderDTO order) 
            throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int insertedId = -1;
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(INSERT_WITH_DISCOUNT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, order.getEmail());
                ps.setString(2, order.getFullname());
                ps.setString(3, order.getAddress());
                ps.setString(4, order.getPhone());
                ps.setDate(5, order.getRentalDate());
                ps.setDate(6, order.getReturnDate());
                ps.setInt(7, order.getDiscount().getId());
                ps.setInt(8, order.getPrice());
                
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
    
    public int insertWithoutDiscount(OrderDTO order) 
            throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int insertedId = -1;
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(INSERT_WITHOUT_DISCOUNT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, order.getEmail());
                ps.setString(2, order.getFullname());
                ps.setString(3, order.getAddress());
                ps.setString(4, order.getPhone());
                ps.setDate(5, order.getRentalDate());
                ps.setDate(6, order.getReturnDate());
                ps.setInt(7, order.getPrice());
                
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
    
    public List<OrderDTO> getOrdersByEmail(String email) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<OrderDTO> orders = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(GET_ORDERS_BY_EMAIL);
                ps.setString(1, email);
                
                rs = ps.executeQuery();
                while (rs.next()) {
                    orders.add(mapResultSetToOrderDTO(rs));
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return orders;
    }
    
    public List<OrderDTO> getOrdersByEmailAndName(String email, String keyword) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<OrderDTO> orders = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(GET_ORDERS_BY_EMAIL_NAME);
                ps.setString(1, email);
                ps.setString(2, "%" + keyword + "%");
                
                rs = ps.executeQuery();
                while (rs.next()) {
                    orders.add(mapResultSetToOrderDTO(rs));
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return orders;
    }
    
    public List<OrderDTO> getOrdersByDate(String email, String startTime, 
            String endTime) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<OrderDTO> orders = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(GET_ORDERS_BY_DATE);
                ps.setString(1, email);
                ps.setString(2, startTime);
                ps.setString(3, endTime);
                
                rs = ps.executeQuery();
                while (rs.next()) {
                    orders.add(mapResultSetToOrderDTO(rs));
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return orders;
    }
    
    public OrderDTO getOrderById(int orderId) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        OrderDTO order = null;
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(GET_ORDER_BY_ID);
                ps.setInt(1, orderId);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    order = mapResultSetToOrderDTO(rs);
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return order;
    }
    
    private OrderDTO mapResultSetToOrderDTO(ResultSet rs) throws SQLException {
        OrderDTO order = new OrderDTO();
        
        order.setId(rs.getInt(OrderParam.ID));
        order.setEmail(rs.getString(OrderParam.EMAIL));
        order.setFullname(rs.getString(OrderParam.FULLNAME));
        order.setAddress(rs.getString(OrderParam.ADDRESS));
        order.setPhone(rs.getString(OrderParam.PHONE));
        order.setRentalDate(rs.getDate(OrderParam.RENTAL_DATE));
        order.setReturnDate(rs.getDate(OrderParam.RETURN_DATE));
        order.setPrice(rs.getInt(OrderParam.PRICE));
        order.setCreateDate(rs.getDate(OrderParam.CREATE_DATE));
        
        DiscountDTO discount = new DiscountDTO();
        discount.setId(rs.getInt(OrderParam.DISCOUNT_ID));
        discount.setDiscount(rs.getDouble(OrderParam.DISCOUNT));
        order.setDiscount(discount);
        
        return order;
    }
}

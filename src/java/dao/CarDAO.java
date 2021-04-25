package dao;

import common.RequestParam.CarParam;
import dto.CarDTO;
import dto.CategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseUtil;

/**
 *
 * @author PC
 */
public class CarDAO {
    private static final String GET_ALL_CARS = "SELECT car.id, car.name, image, "
            + "color, year, price, quantity, branch, "
            + "cat.id AS category_id, cat.name AS category_name, rating "
            + "FROM car "
            + "INNER JOIN category cat ON car.category_id = cat.id "
            + "ORDER BY create_date "
            + "DESC LIMIT ?, ?";
    
    private static final String GET_CARS_BY_NAME = "SELECT car.id, car.name, image, "
            + "color, year, price, quantity, branch, "
            + "cat.id AS category_id, cat.name AS category_name, rating "
            + "FROM car "
            + "INNER JOIN category cat ON car.category_id = cat.id "
            + "WHERE car.name LIKE ? "
            + "ORDER BY create_date "
            + "DESC LIMIT ?, ?";
    
    private static final String GET_CARS_BY_CATEGORY = "SELECT car.id, car.name, image, "
            + "color, year, price, quantity, branch, "
            + "cat.id AS category_id, cat.name AS category_name, rating "
            + "FROM car "
            + "INNER JOIN category cat ON car.category_id = cat.id "
            + "WHERE car.category_id = ? "
            + "ORDER BY create_date "
            + "DESC LIMIT ?, ?";
    
    private static final String GET_CAR_BY_ID = "SELECT car.id, car.name, image, "
            + "color, year, price, quantity, branch, "
            + "cat.id AS category_id, cat.name AS category_name, rating "
            + "FROM car "
            + "INNER JOIN category cat ON car.category_id = cat.id "
            + "WHERE car.id = ?";
    
    private static final String COUNT_ALL_CARS = "SELECT count(*) FROM car";
    
    private static final String COUNT_CARS_BY_NAME = "SELECT count(*) FROM car WHERE name LIKE ?";
    
    private static final String COUNT_CARS_BY_CATEGORY = "SELECT count(*) FROM car "
            + "WHERE category_id = ?";
    
    private static final String UPDATE_CAR_QUANTITY = "UPDATE car SET quantity = ? "
            + "WHERE id = ?";
    
    public List<CarDTO> getAllCars(int offset, int length) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<CarDTO> cars = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(GET_ALL_CARS);
                ps.setInt(1, offset);
                ps.setInt(2, length);
                
                rs = ps.executeQuery();
                while(rs.next()) {
                    cars.add(mapResultSetToCarDTO(rs));
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return cars;
    }
    
    public int countAllCars() 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int total = 0;
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(COUNT_ALL_CARS);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return total;
    }
    
    public List<CarDTO> getCarsByName(String keyword, int offset, int length) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<CarDTO> cars = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(GET_CARS_BY_NAME);
                ps.setString(1, "%" + keyword + "%");
                ps.setInt(2, offset);
                ps.setInt(3, length);
                
                rs = ps.executeQuery();
                while(rs.next()) {
                    cars.add(mapResultSetToCarDTO(rs));
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return cars;
    }
    
    public int countCarsByName(String keyword) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int total = 0;
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(COUNT_CARS_BY_NAME);
                ps.setString(1, "%" + keyword + "%");
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return total;
    }
    
    public List<CarDTO> getCarByCategory(int categoryId, int offset, int length) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<CarDTO> cars = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(GET_CARS_BY_CATEGORY);
                ps.setInt(1, categoryId);
                ps.setInt(2, offset);
                ps.setInt(3, length);
                
                rs = ps.executeQuery();
                while(rs.next()) {
                    cars.add(mapResultSetToCarDTO(rs));
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return cars;
    }
    
    public int countCarsByCategory(int categoryId) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int total = 0;
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(COUNT_CARS_BY_CATEGORY);
                ps.setInt(1, categoryId);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return total;
    }
    
    public CarDTO getCarById(int carId) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        CarDTO car = null;
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(GET_CAR_BY_ID);
                ps.setInt(1, carId);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    car = mapResultSetToCarDTO(rs);
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return car;
    }
    
    public boolean updateCarQuantity(int id, int quantity) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        boolean updated = false;
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_CAR_QUANTITY);
                ps.setInt(1, quantity);
                ps.setInt(2, id);
                
                updated = ps.executeUpdate() > 0;
            }
            
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return updated;
    }
    
    private CarDTO mapResultSetToCarDTO(ResultSet rs) throws SQLException {
        CarDTO car = new CarDTO();
        
        car.setId(rs.getInt(CarParam.ID));
        car.setName(rs.getString(CarParam.NAME));
        car.setImage(rs.getString(CarParam.IMAGE));
        car.setColor(rs.getString(CarParam.COLOR));
        car.setYear(rs.getInt(CarParam.YEAR));
        car.setPrice(rs.getInt(CarParam.PRICE));
        car.setQuantity(rs.getInt(CarParam.QUANTITY));
        car.setBranch(rs.getString(CarParam.BRANCH));
        car.setRating(rs.getDouble(CarParam.RATING));
        
        CategoryDTO cat = new CategoryDTO();
        cat.setId(rs.getInt(CarParam.CATEGORY_ID));
        cat.setName(rs.getString(CarParam.CATEGORY_NAME));
        car.setCategory(cat);
        
        return car;
    }
}

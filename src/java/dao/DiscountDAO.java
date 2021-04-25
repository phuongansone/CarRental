package dao;

import common.RequestParam.DiscountParam;
import dto.DiscountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DatabaseUtil;

/**
 *
 * @author PC
 */
public class DiscountDAO {
    private static final String GET_DISCOUNT_BY_CODE = "SELECT id, code, discount, "
            + "effective_date, expiration_date, quantity "
            + "FROM discount "
            + "WHERE code = ?";
    
    private static final String UPDATE_DISCOUNT_BY_ID = "UPDATE discount "
            + "SET quantity = ? WHERE id = ?";
    
    public DiscountDTO getDiscountByCode(String code) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        DiscountDTO discount = null;
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(GET_DISCOUNT_BY_CODE);
                ps.setString(1, code);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    discount = mapResultSetToDiscount(rs);
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return discount;
    }
    
    public boolean updateDiscountById(int id, int quantity) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        
        boolean updated = false;
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_DISCOUNT_BY_ID);
                ps.setInt(1, quantity);
                ps.setInt(2, id);
                
                updated = ps.executeUpdate() > 0;
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, null);
        }
        
        return updated;
    }
    
    private DiscountDTO mapResultSetToDiscount(ResultSet rs) throws SQLException {
        DiscountDTO discount = new DiscountDTO();
        
        discount.setId(rs.getInt(DiscountParam.ID));
        discount.setCode(rs.getString(DiscountParam.CODE));
        discount.setDiscount(rs.getDouble(DiscountParam.DISCOUNT));
        discount.setEffectiveDate(rs.getDate(DiscountParam.EFFECTIVE_DATE));
        discount.setExpirationDate(rs.getDate(DiscountParam.EXPIRATION_DATE));
        discount.setQuantity(rs.getInt(DiscountParam.QUANTITY));
        
        return discount;
    }
}

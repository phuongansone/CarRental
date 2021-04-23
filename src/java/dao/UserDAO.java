package dao;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DatabaseUtil;

/**
 *
 * @author PC
 */
public class UserDAO {
    private static final String INSERT_USER = "INSERT INTO USER "
            + "(email, phone, name, address, password, role_id, status_id) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    private static final String CHECK_EMAIL_EXISTED = "SELECT count(*) "
            + "FROM user WHERE email = ?";
    
    public int insertUser(UserDTO user) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int insertedId = -1;
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPhone());
                ps.setString(3, user.getName());
                ps.setString(4, user.getAddress());
                ps.setString(5, user.getPassword());
                ps.setInt(6, user.getRole().getId());
                ps.setInt(7, user.getUserStatus().getId());
                
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
    
    public boolean checkEmailExisted(String email) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        boolean existed = false;
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(CHECK_EMAIL_EXISTED);
                ps.setString(1, email);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    existed = rs.getInt(1) > 0;
                }
            }
            
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return existed;
    }
}

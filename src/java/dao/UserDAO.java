package dao;

import common.RequestParam.UserParam;
import dto.RoleDTO;
import dto.UserDTO;
import dto.UserStatusDTO;
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
    
    private static final String CHECK_USER_CREDENTIAL = "SELECT u.email, u.phone, "
            + "u.name, u.address, "
            + "r.id AS role_id, r.name AS role_name, "
            + "s.id AS status_id, s.name AS status_name "
            + "FROM user u "
            + "INNER JOIN role r ON u.role_id = r.id "
            + "INNER JOIN user_status s ON u.status_id = s.id "
            + "WHERE u.email = ? AND password = ?";
    
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
    
    public UserDTO checkUserCredential(String email, String password) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        UserDTO user = null;
        
        try {
            conn = DatabaseUtil.makeConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CHECK_USER_CREDENTIAL);
                ps.setString(1, email);
                ps.setString(2, password);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    user = mapResultSetToUserDTO(rs);
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return user;
    }
    
    private UserDTO mapResultSetToUserDTO(ResultSet rs) throws SQLException {
        UserDTO user = new UserDTO();
        
        user.setEmail(rs.getString(UserParam.EMAIL));
        user.setPhone(rs.getString(UserParam.PHONE));
        user.setName(rs.getString(UserParam.NAME));
        user.setAddress(rs.getString(UserParam.ADDRESS));
        
        RoleDTO role = new RoleDTO();
        role.setId(rs.getInt(UserParam.ROLE_ID));
        role.setName(rs.getString(UserParam.ROLE_NAME));
        user.setRole(role);
        
        UserStatusDTO status = new UserStatusDTO();
        status.setId(rs.getInt(UserParam.STATUS_ID));
        status.setName(rs.getString(UserParam.STATUS_NAME));
        user.setUserStatus(status);
        
        return user;
    }
}

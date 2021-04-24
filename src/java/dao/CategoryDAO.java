package dao;

import common.RequestParam.CategoryParam;
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
public class CategoryDAO {
    private static final String GET_ALL_CATEGORIES = "SELECT id, name, description "
            + "FROM category";
    
    public List<CategoryDTO> getAllCategories() 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<CategoryDTO> categories = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(GET_ALL_CATEGORIES);
                
                rs = ps.executeQuery();
                while(rs.next()) {
                    categories.add(mapResultSetToCategoryDTO(rs));
                }
            }
        } finally {
            DatabaseUtil.closeConnection(conn, ps, rs);
        }
        
        return categories;
    }
    
    private CategoryDTO mapResultSetToCategoryDTO(ResultSet rs) 
            throws SQLException {
        CategoryDTO category = new CategoryDTO();
        
        category.setId(rs.getInt(CategoryParam.ID));
        category.setName(rs.getString(CategoryParam.NAME));
        category.setDescription(rs.getString(CategoryParam.DESCRIPTION));
        
        return category;
    }
}

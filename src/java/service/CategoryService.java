package service;

import dao.CategoryDAO;
import dto.CategoryDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PC
 */
public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService() {
        categoryDAO = new CategoryDAO();
    }
    
    public List<CategoryDTO> getAllCategories() 
            throws SQLException, ClassNotFoundException {
        return categoryDAO.getAllCategories();
    }
}

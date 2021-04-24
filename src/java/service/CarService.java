package service;

import dao.CarDAO;
import dto.CarDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PC
 */
public class CarService {
    private final CarDAO carDAO;

    public CarService() {
        this.carDAO = new CarDAO();
    }
    
    public List<CarDTO> getAllCars(int offset, int length) 
            throws SQLException, ClassNotFoundException {
        return carDAO.getAllCars(offset, length);
    }
    
    public int countAllCars() 
            throws SQLException, ClassNotFoundException {
        return carDAO.countAllCars();
    }
    
    public List<CarDTO> getCarsByName(String keyword, int offset, int length) 
            throws SQLException, ClassNotFoundException {
        return carDAO.getCarsByName(keyword, offset, length);
    }
    
    public int countCarsByName(String keyword) 
            throws SQLException, ClassNotFoundException {
        return carDAO.countCarsByName(keyword);
    }
    
    public List<CarDTO> getCarByCategory(int categoryId, int offset, int length) 
            throws SQLException, ClassNotFoundException {
        return carDAO.getCarByCategory(categoryId, offset, length);
    }
    
    public int countCarsByCategory(int categoryId) 
            throws SQLException, ClassNotFoundException {
        return carDAO.countCarsByCategory(categoryId);
    }
}

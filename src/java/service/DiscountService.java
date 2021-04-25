package service;

import dao.DiscountDAO;
import dto.DiscountDTO;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class DiscountService {
    private final DiscountDAO discountDAO;

    public DiscountService() {
        this.discountDAO = new DiscountDAO();
    }
    
    public DiscountDTO getDiscountByCode(String code) 
            throws SQLException, ClassNotFoundException {
        return discountDAO.getDiscountByCode(code);
    }
    
    public boolean checkDiscountIsEffective(DiscountDTO discount) {
        if (discount == null) {
            return false;
        }
        
        return discount.getEffectiveDate()
                .compareTo(new Date(System.currentTimeMillis())) <= 0;
    }
    
    public boolean checkDiscountIsNotExpired(DiscountDTO discount) {
        if (discount == null) {
            return false;
        }
        
        return discount.getExpirationDate()
                .compareTo(new Date(System.currentTimeMillis())) >= 0;
    }
    
    public boolean checkDiscountAvalaibility(DiscountDTO discount) {
        if (discount == null) {
            return false;
        }
        
        return discount.getQuantity() > 0;
    }
    
    public boolean updateDiscountById(int id, int quantity) 
            throws SQLException, ClassNotFoundException {
        return discountDAO.updateDiscountById(id, quantity);
    }
}

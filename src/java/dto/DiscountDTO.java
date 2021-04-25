package dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author PC
 */
public class DiscountDTO implements Serializable {
    private int id;
    private double discount;
    private Date effectiveDate;
    private Date expirationDate;
    private int quantity;

    public DiscountDTO() {
    }

    public DiscountDTO(int id, double discount, Date effectiveDate, 
            Date expirationDate, int quantity) {
        this.id = id;
        this.discount = discount;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}

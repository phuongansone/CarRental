package dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author PC
 */
public class OrderDTO implements Serializable {
    private int id;
    private String email;
    private String fullname;
    private String address;
    private Date rentalDate;
    private Date returnDate;
    private int price;
    private Date createDate;
    private DiscountDTO discount;

    public OrderDTO() {
    }

    public OrderDTO(int id, String email, String fullname, String address, 
            Date rentalDate, Date returnDate, int price, Date createDate, 
            DiscountDTO discount) {
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.address = address;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.price = price;
        this.createDate = createDate;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public DiscountDTO getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountDTO discount) {
        this.discount = discount;
    }
    
    
}

package dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public class OrderDTO implements Serializable {
    private int id;
    private String email;
    private String fullname;
    private String address;
    private String phone;
    private Date rentalDate;
    private Date returnDate;
    private int price;
    private Date createDate;
    private boolean status;
    private DiscountDTO discount;
    private List<OrderDetailDTO> orderDetails;
            
    public OrderDTO() {
    }

    public OrderDTO(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}

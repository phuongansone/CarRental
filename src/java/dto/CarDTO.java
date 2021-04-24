package dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author PC
 */
public class CarDTO implements Serializable {
    private int id;
    private String name;
    private String image;
    private String color;
    private int year;
    private int price;
    private int quantity;
    private String branch;
    private boolean isAvailable;
    private CategoryDTO category;
    private double rating;
    private Date createDate;

    public CarDTO() {
    }

    public CarDTO(int id, String name, String image, String color, int year, 
            int price, int quantity, String branch, boolean isAvailable, 
            CategoryDTO category, double rating, Date createDate) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.color = color;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.branch = branch;
        this.isAvailable = isAvailable;
        this.category = category;
        this.rating = rating;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    
}

package dto;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class OrderDetailDTO implements Serializable {
    private int id;
    private OrderDTO order;
    private CarDTO car;
    private int quantity;
    private int rating;
    private boolean outOfStock;
    private int price;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int id, OrderDTO order, 
            CarDTO car, int quantity, boolean outOfStock) {
        this.id = id;
        this.order = order;
        this.car = car;
        this.quantity = quantity;
        this.outOfStock = outOfStock;
    }

    public OrderDetailDTO(CarDTO car, int quantity) {
        this.car = car;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(boolean outOfStock) {
        this.outOfStock = outOfStock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

package model;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * Order Entity class to store all the order details
 */
public class Order {
    private final String id;
    private final LocalTime time;
    private final Stock stock;
    private final OrderType orderType;
    private final BigDecimal price;
    private int quantity;

    public Order(String id, LocalTime time, Stock stock, OrderType orderType, BigDecimal price, int quantity) {
        this.id = id;
        this.time = time;
        this.stock = stock;
        this.orderType = orderType;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    public Stock getStock() {
        return stock;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

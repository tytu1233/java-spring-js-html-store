package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Order;

import java.time.LocalDate;

public class OrderBuilder {

    private final Order order;

    public OrderBuilder(int id) {
        this.order = new Order(id);
    }

    public OrderBuilder() {
        this.order = new Order(Constants.INVALID_ID);
    }

    public OrderBuilder setStatus(String status) {
        this.order.setStatus(status);
        return this;
    }

    public OrderBuilder setDate(LocalDate date) {
        this.order.setDate(date);
        return this;
    }

    public OrderBuilder setFinalPrice(double finalPrice) {
        this.order.setFinalPrice(finalPrice);
        return this;
    }

    public OrderBuilder setAddress(String address) {
        this.order.setAddress(address);
        return this;
    }

    public OrderBuilder setUserId(int userId) {
        this.order.setUserId(userId);
        return this;
    }

    public Order getOrder() {
        return this.order;
    }
}

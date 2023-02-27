package pl.britenet.consoleapp.obj.model;

import pl.britenet.consoleapp.Constants;

public class OrderProduct {

    private final int id;
    private int quantity;
    private Double price;
    private int orderId;
    private int productId;

    public OrderProduct(int id) {
        this.id = id;
    }

    public OrderProduct() {
        this.id = Constants.INVALID_ID;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}

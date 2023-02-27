package pl.britenet.consoleapp.obj.model;

import pl.britenet.consoleapp.Constants;

public final class Cart {

    private final int id;
    private int userId;
    private Double finalPrice;

    public Cart(int id) {
        this.id = id;
    }

    public Cart() {
        this.id = Constants.INVALID_ID;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

}

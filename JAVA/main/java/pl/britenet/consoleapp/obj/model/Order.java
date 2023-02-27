package pl.britenet.consoleapp.obj.model;

import pl.britenet.consoleapp.Constants;

import java.time.LocalDate;

public final class Order {

    private final int id;

    private String status;
    private LocalDate date;
    private double finalPrice;

    private String address;
    private int userId;

    public Order(int id) {
        this.id = id;
    }

    public Order() {
        this.id = Constants.INVALID_ID;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double final_price) {
        this.finalPrice = final_price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

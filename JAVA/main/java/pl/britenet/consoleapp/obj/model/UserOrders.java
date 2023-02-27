package pl.britenet.consoleapp.obj.model;

import pl.britenet.consoleapp.Constants;

public class UserOrders {
    private final int id_order;
    private Order order;
    private User user;

    private String month;

    public UserOrders(int id_order) {
        this.id_order = id_order;
    }

    public UserOrders() {
        this.id_order = Constants.INVALID_ID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}

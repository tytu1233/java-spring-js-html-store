package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.UserOrders;
import pl.britenet.consoleapp.obj.model.Order;
import pl.britenet.consoleapp.obj.model.User;

public class UserOrdersBuilder {

    private final UserOrders userOrders;

    public UserOrdersBuilder(int id) {
        this.userOrders = new UserOrders(id);
    }

    public UserOrdersBuilder setOrder(Order order) {
        this.userOrders.setOrder(order);
        return this;
    }

    public UserOrdersBuilder setUser(User user) {
        this.userOrders.setUser(user);
        return this;
    }

    public UserOrdersBuilder setMonth(String month) {
        this.userOrders.setMonth(month);
        return this;
    }

    public UserOrders getUserOrders() {
        return this.userOrders;
    }
}

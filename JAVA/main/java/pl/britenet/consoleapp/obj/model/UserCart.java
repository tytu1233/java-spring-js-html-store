package pl.britenet.consoleapp.obj.model;

import pl.britenet.consoleapp.Constants;

public class UserCart {

    private final int user_id;
    private final int cart_id;

    private Cart cart;
    private User user;

    public UserCart(int user_id, int cart_id) {
        this.user_id = user_id;
        this.cart_id = cart_id;
    }

    public UserCart() {
        this.user_id = Constants.INVALID_ID;
        this.cart_id = Constants.INVALID_ID;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

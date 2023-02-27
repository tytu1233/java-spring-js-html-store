package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.obj.model.User;
import pl.britenet.consoleapp.obj.model.UserCart;

public class UserCartBuilder {

    private final UserCart userCart;

    public UserCartBuilder(int user_id, int cart_id) {
        this.userCart = new UserCart(user_id, cart_id);
    }

    public UserCartBuilder setUser(User user) {
        this.userCart.setUser(user);
        return this;
    }

    public UserCartBuilder setCart(Cart cart) {
        this.userCart.setCart(cart);
        return this;
    }

    public UserCart getUserCart() {
        return this.userCart;
    }

}

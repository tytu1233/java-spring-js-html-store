package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Cart;

public class CartBuilder {

    private final Cart cart;

    public CartBuilder(int id) {
        this.cart = new Cart(id);
    }

    public CartBuilder() {
        this.cart = new Cart(Constants.INVALID_ID);
    }

    public CartBuilder setUserId(int id) {
        this.cart.setUserId(id);
        return this;
    }

    public CartBuilder setFinalPrice(Double finalPrice) {
        this.cart.setFinalPrice(finalPrice);
        return this;
    }

    public Cart getCart() {
        return this.cart;
    }

}

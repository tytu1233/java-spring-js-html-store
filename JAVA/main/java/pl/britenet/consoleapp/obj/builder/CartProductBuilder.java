package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.CartProduct;

public class CartProductBuilder {

    private final CartProduct cartProduct;

    public CartProductBuilder(int id) {
        this.cartProduct = new CartProduct(id);
    }

    public CartProductBuilder() {
        this.cartProduct = new CartProduct(Constants.INVALID_ID);
    }

    public CartProductBuilder setCartId(int cartId) {
        this.cartProduct.setCartId(cartId);
        return this;
    }

    public CartProductBuilder setProductId(int productId) {
        this.cartProduct.setProductId(productId);
        return this;
    }

    public CartProductBuilder setQuantity(int quantity) {
        this.cartProduct.setQuantity(quantity);
        return this;
    }

    public CartProduct getCartProduct() {
        return this.cartProduct;
    }
}

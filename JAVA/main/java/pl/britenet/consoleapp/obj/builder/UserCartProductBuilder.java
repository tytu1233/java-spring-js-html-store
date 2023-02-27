package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.obj.model.UserCartProduct;

public class UserCartProductBuilder {

    private UserCartProduct userCartProduct;

    public UserCartProductBuilder() {
        this.userCartProduct = new UserCartProduct();
    }

    public UserCartProductBuilder setProduct(Product product) {
        this.userCartProduct.setProduct(product);
        return this;
    }
    public UserCartProductBuilder setCart(Cart cart) {
        this.userCartProduct.setCart(cart);
        return this;
    }
    public UserCartProductBuilder setCartProduct(CartProduct cartProduct) {
        this.userCartProduct.setCartProduct(cartProduct);
        return this;
    }

    public UserCartProduct getUserCartProduct() {
        return this.userCartProduct;
    }

}

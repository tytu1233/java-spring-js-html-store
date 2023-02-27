package pl.britenet.consoleapp.obj.model;

import pl.britenet.consoleapp.Constants;

public class CartProduct {

    private final int id;
    private int cartId;
    private int productId;
    private int quantity;

    public CartProduct(int id) {
        this.id = id;
    }

    public CartProduct() {
        this.id = Constants.INVALID_ID;
    }

    public int getId() {
        return id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

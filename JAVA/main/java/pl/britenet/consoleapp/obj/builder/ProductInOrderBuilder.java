package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.ProductInOrder;

public class ProductInOrderBuilder {
    private final ProductInOrder productInOrder;

    public ProductInOrderBuilder() {
        this.productInOrder = new ProductInOrder();
    }

    public ProductInOrderBuilder setQuantity(int quantity) {
        this.productInOrder.setQuantity(quantity);
        return this;
    }

    public ProductInOrderBuilder setPrice(double price) {
        this.productInOrder.setPrice(price);
        return this;
    }

    public ProductInOrderBuilder setName(String name) {
        this.productInOrder.setName(name);
        return this;
    }

    public ProductInOrderBuilder setImage(String image) {
        this.productInOrder.setImage(image);
        return this;
    }

    public ProductInOrder getProductInOrder() {
        return this.productInOrder;
    }
}

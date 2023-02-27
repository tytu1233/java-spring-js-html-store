package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Product;

public class ProductBuilder {

    private final Product product;

    public ProductBuilder(int id) {
        this.product = new Product(id);
    }

    public ProductBuilder() {
        this.product = new Product(Constants.INVALID_ID);
    }

    public ProductBuilder setName(String name) {
        this.product.setName(name);
        return this;
    }

    public ProductBuilder setDescription(String description) {
        this.product.setDescription(description);
        return this;
    }

    public ProductBuilder setPrice(Double price) {
        this.product.setPrice(price);
        return this;
    }

    public ProductBuilder setImage(String image) {
        this.product.setImage(image);
        return this;
    }

    public Product getProduct() {
        return this.product;
    }
}

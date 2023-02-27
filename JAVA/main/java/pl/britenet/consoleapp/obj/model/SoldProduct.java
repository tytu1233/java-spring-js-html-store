package pl.britenet.consoleapp.obj.model;

import pl.britenet.consoleapp.Constants;

public class SoldProduct {

    private final int id_product;
    private int amount;
    private Product product;

    public SoldProduct(int id_product) {
        this.id_product = id_product;
    }

    public SoldProduct() {
        this.id_product = Constants.INVALID_ID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

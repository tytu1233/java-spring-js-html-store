package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.obj.model.SoldProduct;

public class SoldProductBuilder {

    private final SoldProduct soldProduct;

    public SoldProductBuilder(int id) {
        this.soldProduct = new SoldProduct(id);
    }

    public SoldProductBuilder() {
        this.soldProduct = new SoldProduct(Constants.INVALID_AMOUNT);
    }


    public SoldProductBuilder setProduct(Product product) {
        this.soldProduct.setProduct(product);
        return this;
    }

    public SoldProductBuilder setAmount(int amount) {
        this.soldProduct.setAmount(amount);
        return this;
    }

    public SoldProduct getSoldProduct() {
        return this.soldProduct;
    }


}

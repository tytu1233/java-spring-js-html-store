package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.HigherQuantity;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.obj.model.Product;

public class HigherQuantityBuilder {

    private final HigherQuantity higherQuantity;

    public HigherQuantityBuilder(int id_order_product, int id_product) {
        this.higherQuantity = new HigherQuantity(id_order_product, id_product);
    }

    public HigherQuantityBuilder setOrderProduct(OrderProduct orderProduct) {
        this.higherQuantity.setOrderProduct(orderProduct);
        return this;
    }

    public HigherQuantityBuilder setProduct(Product product) {
        this.higherQuantity.setProduct(product);
        return this;
    }

    public HigherQuantity getHigherQuantity() {
        return this.higherQuantity;
    }
}

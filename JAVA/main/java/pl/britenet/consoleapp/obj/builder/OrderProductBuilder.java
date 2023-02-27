package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.OrderProduct;

public class OrderProductBuilder {

    private final OrderProduct orderProduct;

    public OrderProductBuilder(int id) {
        this.orderProduct = new OrderProduct(id);
    }

    public OrderProductBuilder() {
        this.orderProduct = new OrderProduct(Constants.INVALID_ID);
    }

    public OrderProductBuilder setQuantity(int quantity) {
        this.orderProduct.setQuantity(quantity);
        return this;
    }

    public OrderProductBuilder setPrice(Double price) {
        this.orderProduct.setPrice(price);
        return this;
    }

    public OrderProductBuilder setOrderId(int orderId) {
        this.orderProduct.setOrderId(orderId);
        return this;
    }

    public OrderProductBuilder setProductId(int productId) {
        this.orderProduct.setProductId(productId);
        return this;
    }

    public OrderProduct getOrderProduct() {
        return this.orderProduct;
    }
}

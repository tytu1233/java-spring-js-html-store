package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.Order;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.obj.model.ReturnedOrders;

public class ReturnedOrdersBuilder {

    private final ReturnedOrders returnedOrders;

    public ReturnedOrdersBuilder(int id_product, int id_order, int id_order_product) {
        this.returnedOrders = new ReturnedOrders(id_product, id_order, id_order_product);
    }

    public ReturnedOrdersBuilder setProduct(Product product) {
        this.returnedOrders.setProduct(product);
        return this;
    }

    public ReturnedOrdersBuilder setOrder(Order order) {
        this.returnedOrders.setOrder(order);
        return this;
    }

    public ReturnedOrdersBuilder setOrderProduct(OrderProduct orderProduct) {
        this.returnedOrders.setOrderProduct(orderProduct);
        return this;
    }

    public ReturnedOrders getReturnedOrders() {
        return this.returnedOrders;
    }
}

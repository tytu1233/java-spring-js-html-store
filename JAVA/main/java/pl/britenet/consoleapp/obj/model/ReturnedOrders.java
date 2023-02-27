package pl.britenet.consoleapp.obj.model;

public class ReturnedOrders {


    private final int id_product;
    private final int id_order;
    private final int id_order_product;
    private Product product;
    private Order order;
    private OrderProduct orderProduct;

    public ReturnedOrders(int id_product, int id_order, int id_order_product) {
        this.id_order = id_order;
        this.id_product = id_product;
        this.id_order_product = id_order_product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }
}

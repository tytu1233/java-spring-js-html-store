package pl.britenet.consoleapp.obj.model;

public class HigherQuantity {

    private final int id_order_product;
    private final int id_product;

    private OrderProduct orderProduct;
    private Product product;

    public HigherQuantity(int id_order_product, int id_product) {
        this.id_order_product = id_order_product;
        this.id_product = id_product;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.OrderProductBuilder;
import pl.britenet.consoleapp.obj.model.OrderProduct;

import java.sql.SQLException;
import java.util.*;

public class OrderProductService {

    Map<Integer, OrderProduct> orderProductMap;
    private final DatabaseService databaseService;

    public OrderProductService(DatabaseService databaseService) {
        this.orderProductMap = new HashMap<>();
        this.databaseService = databaseService;
    }

    public void insertOrderProduct(OrderProduct orderProduct) {
        this.databaseService.performDML(
                String.format(Locale.US, "INSERT INTO order_product (quantity, price, order_id, product_id) VALUES('%d', '%.2f', '%d', '%d')",
                        orderProduct.getQuantity(),
                        orderProduct.getPrice(),
                        orderProduct.getOrderId(),
                        orderProduct.getProductId())

        );
    }

    public Optional<OrderProduct> getOrderProduct(int id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM order_product WHERE order_id='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            int quantity = resultSet.getInt("quantity");
                            Double price = resultSet.getDouble("price");
                            int orderId = resultSet.getInt("order_id");
                            int productId = resultSet.getInt("product_id");


                            return new OrderProductBuilder(id)
                                    .setQuantity(quantity)
                                    .setPrice(price)
                                    .setOrderId(orderId)
                                    .setProductId(productId)
                                    .getOrderProduct();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    public Optional<Collection<OrderProduct>> getAllOrderProducts(int cart_id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM order_product WHERE order_id='%d'", cart_id),
                resultSet -> {
                    try {
                        Collection<OrderProduct> orderProductCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id = resultSet.getInt("id_order_product");
                            int quantity = resultSet.getInt("quantity");
                            Double price = resultSet.getDouble("price");
                            int orderId = resultSet.getInt("order_id");
                            int productId = resultSet.getInt("product_id");


                            OrderProduct orderProduct = new OrderProductBuilder(id)
                                    .setQuantity(quantity)
                                    .setPrice(price)
                                    .setOrderId(orderId)
                                    .setProductId(productId)
                                    .getOrderProduct();

                            orderProductCollection.add(orderProduct);
                        }
                        return orderProductCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }

    public void updateOrderProduct(OrderProduct orderProduct) {
        this.databaseService.performDML(
                String.format(Locale.US,"UPDATE order_product SET quantity='%d', price='%.2f', order_id='%d', product_id='%d' WHERE id_order_product='%d'",
                        orderProduct.getQuantity(),
                        orderProduct.getPrice(),
                        orderProduct.getOrderId(),
                        orderProduct.getProductId(),
                        orderProduct.getId())
        );
    }

    public void deleteOrderProduct(OrderProduct orderProduct) {
        this.databaseService.performDML(
                String.format("DELETE FROM order_product WHERE id_order_product='%d'",
                        orderProduct.getId())
        );

    }

}

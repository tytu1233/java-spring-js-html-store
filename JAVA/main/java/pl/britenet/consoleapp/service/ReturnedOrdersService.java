package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.*;
import pl.britenet.consoleapp.obj.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ReturnedOrdersService {

    private final DatabaseService databaseService;

    public ReturnedOrdersService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Collection<ReturnedOrders>> getReturnedOrders() {
        return this.databaseService.performSQL(
                "SELECT p.id_product, o.id_order, p.name, p.price, op.quantity, op.id_order_product\n" +
                        "FROM order_product op \n" +
                        "JOIN orders o ON op.order_id=o.id_order\n" +
                        "JOIN product p ON op.product_id=p.id_product\n" +
                        "WHERE o.status=\"ZWROT\"",
                resultSet -> {
                    try {
                        Collection<ReturnedOrders> returnedOrdersCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id_product = resultSet.getInt("p.id_product");
                            int id_order = resultSet.getInt("o.id_order");
                            int id_order_product = resultSet.getInt("op.id_order_product");
                            String name = resultSet.getString("p.name");
                            Double price = resultSet.getDouble("p.price");
                            int quantity = resultSet.getInt("op.quantity");

                            Product product = new ProductBuilder(id_product)
                                    .setName(name)
                                    .setPrice(price)
                                    .getProduct();

                            Order order = new OrderBuilder(id_order)
                                    .getOrder();

                            OrderProduct orderProduct = new OrderProductBuilder(id_order_product)
                                    .setQuantity(quantity)
                                    .getOrderProduct();

                            ReturnedOrders returnedOrders = new ReturnedOrdersBuilder(id_product, id_order, id_order_product)
                                    .setOrder(order)
                                    .setOrderProduct(orderProduct)
                                    .setProduct(product)
                                    .getReturnedOrders();

                            returnedOrdersCollection.add(returnedOrders);
                        }
                        return returnedOrdersCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }
}

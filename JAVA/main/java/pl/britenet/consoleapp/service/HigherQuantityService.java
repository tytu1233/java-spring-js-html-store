package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.HigherQuantityBuilder;
import pl.britenet.consoleapp.obj.builder.OrderProductBuilder;
import pl.britenet.consoleapp.obj.builder.ProductBuilder;
import pl.britenet.consoleapp.obj.model.HigherQuantity;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.obj.model.Product;

import java.sql.SQLException;
import java.util.*;

public class HigherQuantityService {

    private final DatabaseService databaseService;

    public HigherQuantityService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Collection<HigherQuantity>> getAllHigherQuantity() {
        return this.databaseService.performSQL(
                "SELECT op.id_order_product, p.id_product, p.name, op.quantity\n" +
                        "FROM product p\n" +
                        "LEFT JOIN order_product op ON p.id_product=op.product_id\n" +
                        "GROUP BY op.id_order_product\n" +
                        "HAVING op.quantity > 3",
                resultSet -> {
                    try {
                        Collection<HigherQuantity> higherQuantitiesCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id_order_product = resultSet.getInt("op.id_order_product");
                            int id_product = resultSet.getInt("p.id_product");
                            int quantity = resultSet.getInt("op.quantity");
                            String name = resultSet.getString("p.name");


                            OrderProduct orderProduct = new OrderProductBuilder(id_order_product)
                                    .setQuantity(quantity)
                                    .getOrderProduct();

                            Product product = new ProductBuilder(id_product)
                                    .setName(name)
                                    .getProduct();

                            HigherQuantity higherQuantity = new HigherQuantityBuilder(id_order_product, id_product)
                                    .setOrderProduct(orderProduct)
                                    .setProduct(product)
                                    .getHigherQuantity();

                            higherQuantitiesCollection.add(higherQuantity);
                        }
                        return higherQuantitiesCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }


    public Optional<Collection<HigherQuantity>> getComplitingProducts() {
        return this.databaseService.performSQL(
                "SELECT p.id_product, op.id_order_product, p.name, SUM(op.quantity)\n" +
                        "FROM orders o\n" +
                        "JOIN order_product op ON o.id_order=op.order_id\n" +
                        "JOIN product p ON op.product_id=p.id_product\n" +
                        "WHERE o.status=\"KOMPLETOWANIE\"\n" +
                        "GROUP BY p.id_product",
                resultSet -> {
                    try {
                        Collection<HigherQuantity> higherQuantitiesCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id_order_product = resultSet.getInt("op.id_order_product");
                            int id_product = resultSet.getInt("p.id_product");
                            int quantity = resultSet.getInt("SUM(op.quantity)");
                            String name = resultSet.getString("p.name");


                            OrderProduct orderProduct = new OrderProductBuilder(id_order_product)
                                    .setQuantity(quantity)
                                    .getOrderProduct();

                            Product product = new ProductBuilder(id_product)
                                    .setName(name)
                                    .getProduct();

                            HigherQuantity higherQuantity = new HigherQuantityBuilder(id_order_product, id_product)
                                    .setOrderProduct(orderProduct)
                                    .setProduct(product)
                                    .getHigherQuantity();

                            higherQuantitiesCollection.add(higherQuantity);
                        }
                        return higherQuantitiesCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }

    public Optional<Collection<HigherQuantity>> getProdcutsAboveAveragePrice() {
        return this.databaseService.performSQL(
                "SELECT p.id_product, op.id_order_product, p.name, p.price, SUM(op.quantity)\n" +
                        "FROM orders o \n" +
                        "JOIN order_product op ON o.id_order=op.product_id\n" +
                        "JOIN product p ON op.product_id=p.id_product\n" +
                        "GROUP BY p.id_product\n" +
                        "HAVING (p.price) > (\n" +
                        "\tSELECT AVG(p.price)\n" +
                        "    FROM product p\n" +
                        ")",
                resultSet -> {
                    try {
                        Collection<HigherQuantity> higherQuantitiesCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id_order_product = resultSet.getInt("op.id_order_product");
                            int id_product = resultSet.getInt("p.id_product");
                            int quantity = resultSet.getInt("SUM(op.quantity)");
                            Double price = resultSet.getDouble("p.price");
                            String name = resultSet.getString("p.name");


                            OrderProduct orderProduct = new OrderProductBuilder(id_order_product)
                                    .setQuantity(quantity)
                                    .getOrderProduct();

                            Product product = new ProductBuilder(id_product)
                                    .setName(name)
                                    .setPrice(price)
                                    .getProduct();

                            HigherQuantity higherQuantity = new HigherQuantityBuilder(id_order_product, id_product)
                                    .setOrderProduct(orderProduct)
                                    .setProduct(product)
                                    .getHigherQuantity();

                            higherQuantitiesCollection.add(higherQuantity);
                        }
                        return higherQuantitiesCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }

}

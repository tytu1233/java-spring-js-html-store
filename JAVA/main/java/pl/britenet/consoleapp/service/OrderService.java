package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.OrderBuilder;
import pl.britenet.consoleapp.obj.model.Order;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class OrderService {

    private final Map<Integer, Order> orderMap;
    private final DatabaseService databaseService;

    public OrderService(DatabaseService databaseService) {
        this.orderMap = new HashMap<>();
        this.databaseService = databaseService;
    }

    public Optional<Collection<Order>> getAllOrders() {
        return this.databaseService.performSQL(
                "SELECT MAX(id_order) as id_order FROM orders LIMIT 1;",
                resultSet -> {
                    try {
                        Collection<Order> ordersCollections = new ArrayList<>();
                        while (resultSet.next()) {

                            int id = resultSet.getInt("id_order");
                            Order order = new OrderBuilder(id)
                                    .getOrder();

                            ordersCollections.add(order);
                        }
                        return ordersCollections;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }


    public Optional<Collection<Order>> getAllOrdersByUserId(int user_id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM orders WHERE user_id='%d'", user_id),
                resultSet -> {
                    try {
                        Collection<Order> ordersCollections = new ArrayList<>();
                        while (resultSet.next()) {

                            int id = resultSet.getInt("id_order");
                            String status = resultSet.getString("status");
                            LocalDate orderDate = LocalDate.parse(resultSet.getString("orderdate"));
                            Double finalPrice = resultSet.getDouble("final_price");
                            String address = resultSet.getString("address");
                            int userId = resultSet.getInt("user_id");
                            Order order = new OrderBuilder(id)
                                    .setStatus(status)
                                    .setDate(orderDate)
                                    .setFinalPrice(finalPrice)
                                    .setAddress(address)
                                    .setUserId(userId)
                                    .getOrder();

                            ordersCollections.add(order);
                        }
                        return ordersCollections;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }


    public void insertOrder(Order order) {
        this.databaseService.performDML(
                String.format(Locale.US,"INSERT INTO orders (status, orderdate, final_price, address, user_id)" +
                        "VALUES('%s', '%s', '%.2f', '%s', '%d')",
                        order.getStatus(),
                        order.getDate(),
                        order.getFinalPrice(),
                        order.getAddress(),
                        order.getUserId())
        );
    }

    public Optional<Order> getOrder(int id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM orders WHERE id_order='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            String status = resultSet.getString("status");
                            LocalDate orderDate = LocalDate.parse(resultSet.getString("orderdate"));
                            Double finalPrice = resultSet.getDouble("final_price");
                            String address = resultSet.getString("address");
                            int userId = resultSet.getInt("user_id");

                            return new OrderBuilder(id)
                                    .setStatus(status)
                                    .setDate(orderDate)
                                    .setFinalPrice(finalPrice)
                                    .setAddress(address)
                                    .setUserId(userId)
                                    .getOrder();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    public void updateOrder(Order order) {
        this.databaseService.performDML(
                String.format(Locale.US, "UPDATE orders SET status='%s', orderdate='%s', final_price='%.2f', address='%s', user_id='%d' WHERE id_order='%d'",
                        order.getStatus(),
                        order.getDate(),
                        order.getFinalPrice(),
                        order.getAddress(),
                        order.getUserId(),
                        order.getId())
        );
    }

    public void deleteOrder(Order order) {
        this.databaseService.performDML(
                String.format("DELETE FROM orders WHERE id_order='%d'",
                        order.getId())
        );
    }
}

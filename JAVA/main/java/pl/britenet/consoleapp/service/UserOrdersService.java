package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.*;
import pl.britenet.consoleapp.obj.model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UserOrdersService {

    private final DatabaseService databaseService;

    public UserOrdersService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Collection<UserOrders>> getUserOrders() {
        return this.databaseService.performSQL(
                "SELECT o.id_order, o.OrderDate, u.id_user, u.Name, u.Surname, u.Login, u.Address\n" +
                        "FROM users u \n" +
                        "RIGHT JOIN orders o ON u.id_user=o.user_id\n" +
                        "WHERE u.Address REGEXP 'Kielce|Warszawa|Gdańsk';",
                resultSet -> {
                    try {
                        Collection<UserOrders> userOrdersCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id = resultSet.getInt("o.id_order");
                            int id_user = resultSet.getInt("u.id_user");
                            LocalDate orderDate = LocalDate.parse(resultSet.getString("o.OrderDate"));
                            String name = resultSet.getString("u.Name");
                            String surname = resultSet.getString("u.Surname");
                            String login = resultSet.getString("u.Login");
                            String address = resultSet.getString("u.Address");

                            Order order = new OrderBuilder(id)
                                    .setDate(orderDate)
                                    .getOrder();

                            User user = new UserBuilder(id_user)
                                    .setName(name)
                                    .setSurname(surname)
                                    .setLogin(login)
                                    .setAddress(address)
                                    .getUser();

                            UserOrders userOrders = new UserOrdersBuilder(id)
                                    .setOrder(order)
                                    .setUser(user)
                                    .getUserOrders();

                            userOrdersCollection.add(userOrders);
                        }
                        return userOrdersCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }


    public Optional<Collection<UserOrders>> getFirstOrder() {
        return this.databaseService.performSQL(
                "\n" +
                        "SELECT o.id_order, u.id_user, u.Name, u.Surname, MIN(o.OrderDate) AS first\n" +
                        "FROM users u \n" +
                        "JOIN orders o ON o.user_id=u.id_user\n" +
                        "GROUP BY u.id_user",
                resultSet -> {
                    try {
                        Collection<UserOrders> userOrdersCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id = resultSet.getInt("o.id_order");
                            int id_user = resultSet.getInt("u.id_user");
                            LocalDate orderDate = LocalDate.parse(resultSet.getString("first"));
                            String name = resultSet.getString("u.Name");
                            String surname = resultSet.getString("u.Surname");

                            Order order = new OrderBuilder(id)
                                    .setDate(orderDate)
                                    .getOrder();

                            User user = new UserBuilder(id_user)
                                    .setName(name)
                                    .setSurname(surname)
                                    .getUser();

                            UserOrders userOrders = new UserOrdersBuilder(id)
                                    .setOrder(order)
                                    .setUser(user)
                                    .getUserOrders();

                            userOrdersCollection.add(userOrders);
                        }
                        return userOrdersCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }


    public Optional<Collection<UserOrders>> getMostOrdersInMonth() {
        return this.databaseService.performSQL(
                "\n" +
                        "SELECT t.uid, t.uname, t.usurname, MONTH(o.OrderDate), t.liczba\n" +
                        "FROM orders o \n" +
                        "JOIN (\n" +
                        "SELECT u.id_user uid, u.Name uname, u.Surname usurname, MONTH(o.OrderDate), COUNT(o.id_order) liczba\n" +
                        "FROM orders o\n" +
                        "JOIN users u  ON o.user_id=u.id_user\n" +
                        "GROUP BY MONTH(o.OrderDate), u.id_user\n" +
                        "ORDER BY COUNT(o.id_order) DESC\n" +
                        ") t ON t.uid=o.user_id\n" +
                        "GROUP BY MONTH(o.OrderDate);",
                resultSet -> {
                    try {
                        Collection<UserOrders> userOrdersCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id = resultSet.getInt("t.uid");
                            String month = resultSet.getString("MONTH(o.OrderDate)");
                            String name = resultSet.getString("t.uname");
                            String surname = resultSet.getString("t.usurname");
                            int sum = resultSet.getInt("t.liczba");

                            Order order = new OrderBuilder()
                                    .setFinalPrice(sum)
                                    .getOrder();

                            User user = new UserBuilder(id)
                                    .setName(name)
                                    .setSurname(surname)
                                    .getUser();

                            UserOrders userOrders = new UserOrdersBuilder(id)
                                    .setOrder(order)
                                    .setUser(user)
                                    .setMonth(month)
                                    .getUserOrders();

                            userOrdersCollection.add(userOrders);
                        }
                        return userOrdersCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }

}

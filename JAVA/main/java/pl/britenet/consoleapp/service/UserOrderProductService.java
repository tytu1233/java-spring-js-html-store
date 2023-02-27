package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.*;
import pl.britenet.consoleapp.obj.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UserOrderProductService {

    private final DatabaseService databaseService;

    public UserOrderProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Collection<UserOrderProduct>> getUserOrderProduct() {
        return this.databaseService.performSQL(
                "SELECT u.id_user, u.Name, u.Surname, u.Login, p.id_product, p.name\n" +
                        "FROM users u\n" +
                        "JOIN orders o ON u.id_user=o.user_id\n" +
                        "JOIN order_product op ON o.id_order=op.order_id\n" +
                        "JOIN product p ON p.id_product=op.product_id\n" +
                        "WHERE p.name LIKE \"Bluz%\";",
                resultSet -> {
                    try {
                        Collection<UserOrderProduct> userOrderProductsCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id_user = resultSet.getInt("u.id_user");
                            int id_product = resultSet.getInt("p.id_product");
                            String name = resultSet.getString("u.Name");
                            String surname = resultSet.getString("u.Surname");
                            String login = resultSet.getString("u.Login");
                            String product_name = resultSet.getString("p.name");

                            Product product = new ProductBuilder(id_product)
                                    .setName(product_name)
                                    .getProduct();

                            User user = new UserBuilder(id_user)
                                    .setName(name)
                                    .setSurname(surname)
                                    .setLogin(login)
                                    .getUser();

                            UserOrderProduct userOrderProduct = new UserOrderProductBuilder(id_user, id_product)
                                    .setProduct(product)
                                    .setUser(user)
                                    .getUserOrderProduct();

                            userOrderProductsCollection.add(userOrderProduct);
                        }
                        return userOrderProductsCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }

}

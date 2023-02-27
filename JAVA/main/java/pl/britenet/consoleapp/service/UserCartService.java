package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.*;
import pl.britenet.consoleapp.obj.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UserCartService {

    private final DatabaseService databaseService;

    public UserCartService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Collection<UserCart>> getAverageCarts() {
        return this.databaseService.performSQL(
                "SELECT u.id_user, u.name, u.surname, c.id_cart, SUM(p.price*cp.quantity) AS suma\n" +
                        "FROM cart_product cp\n" +
                        "JOIN product p ON cp.product_id=p.id_product\n" +
                        "JOIN cart c ON c.id_cart=cp.cart_id\n" +
                        "JOIN users u ON u.id_user=c.user_id\n" +
                        "GROUP BY cp.cart_id\n" +
                        "HAVING(SUM(p.price*cp.quantity)) > (\n" +
                        "    SELECT asset_sums/num\n" +
                        "\tFROM cart c\n" +
                        "    JOIN (\n" +
                        "        SELECT cp.cart_id cpcid, COUNT(cp.cart_id) AS num, SUM(cp.quantity*p.price) AS asset_sums\n" +
                        "        FROM cart_product cp\n" +
                        "        JOIN product p ON cp.product_id=p.id_product\n" +
                        "\t) a ON c.id_cart=a.cpcid\n" +
                        ")",
                resultSet -> {
                    try {
                        Collection<UserCart> userCartsCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id_user = resultSet.getInt("u.id_user");
                            String name = resultSet.getString("u.name");
                            String surname = resultSet.getString("u.surname");
                            int cart_id = resultSet.getInt("c.id_cart");
                            Double sum = resultSet.getDouble("suma");

                            User user = new UserBuilder(id_user)
                                    .setName(name)
                                    .setSurname(surname)
                                    .getUser();

                            Cart cart = new CartBuilder(cart_id)
                                    .setFinalPrice(sum)
                                    .getCart();

                            UserCart userCart = new UserCartBuilder(id_user, cart_id)
                                    .setCart(cart)
                                    .setUser(user)
                                    .getUserCart();

                            userCartsCollection.add(userCart);
                        }
                        return userCartsCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }


}

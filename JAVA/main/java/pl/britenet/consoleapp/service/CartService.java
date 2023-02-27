package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.CartBuilder;
import pl.britenet.consoleapp.obj.model.Cart;

import java.sql.SQLException;
import java.util.*;

public class CartService {

    private final Map<Integer, Cart> cartMap;
    private final DatabaseService databaseService;

    public CartService(DatabaseService databaseService) {
        this.cartMap = new HashMap<>();
        this.databaseService = databaseService;
    }

    public void insertCart(Cart cart) {
        this.databaseService.performDML(
                String.format(Locale.US,"INSERT INTO CART (user_id, final_price) " +
                        "VALUES('%d', '%.2f')",
                        cart.getUserId(),
                        cart.getFinalPrice())
        );
    }

    public Optional<Cart> getCart(int id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM cart WHERE id_cart='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            int userId = resultSet.getInt("user_id");
                            Double price = resultSet.getDouble("final_price");

                            return new CartBuilder(id)
                                    .setUserId(userId)
                                    .setFinalPrice(price)
                                    .getCart();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    public Optional<Cart> getCartByUserId(int id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM cart WHERE user_id='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            int cart_id = resultSet.getInt("id_cart");
                            int userId = resultSet.getInt("user_id");
                            Double price = resultSet.getDouble("final_price");

                            return new CartBuilder(cart_id)
                                    .setUserId(userId)
                                    .setFinalPrice(price)
                                    .getCart();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }


    public Optional<Collection<Cart>> getAllCarts() {
        return this.databaseService.performSQL(
                "SELECT * FROM cart",
                resultSet -> {
                    try {
                        Collection<Cart> cartCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id = resultSet.getInt("id_cart");
                            int userId = resultSet.getInt("user_id");
                            Double finalPrice = resultSet.getDouble("final_price");

                            Cart cart = new CartBuilder(id)
                                    .setUserId(userId)
                                    .setFinalPrice(finalPrice)
                                    .getCart();

                            cartCollection.add(cart);
                        }
                        return cartCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }

    public void updateCart(Cart cart) {
        this.databaseService.performDML(
        String.format(Locale.US,"UPDATE cart SET user_id='%d', final_price='%.2f' WHERE id_cart='%d'",
                cart.getUserId(),
                cart.getFinalPrice(),
                cart.getId())
        );
    }

    public void deleteCart(Cart cart) {
        this.databaseService.performDML(
                String.format("DELETE FROM cart WHERE id_cart='%d'",
                        cart.getId())
        );

    }

}

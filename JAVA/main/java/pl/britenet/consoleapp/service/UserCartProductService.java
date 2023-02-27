package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.CartBuilder;
import pl.britenet.consoleapp.obj.builder.CartProductBuilder;
import pl.britenet.consoleapp.obj.builder.ProductBuilder;
import pl.britenet.consoleapp.obj.builder.UserCartProductBuilder;
import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.obj.model.UserCartProduct;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UserCartProductService {

    private final DatabaseService databaseService;

    public UserCartProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Collection<UserCartProduct>> getCartProducts(int user_id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM cart_product cp\n" +
                        "JOIN product p ON cp.product_id=p.id_product\n" +
                        "JOIN cart c ON cp.cart_id=c.id_cart\n" +
                        "WHERE c.user_id='%d'", user_id),
                resultSet -> {
                    try {
                        Collection<UserCartProduct> userCartsCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int product_id = resultSet.getInt("product_id");
                            int id_cart = resultSet.getInt("id_cart");
                            int cart_product_id = resultSet.getInt("id_cart_product");
                            int quantity = resultSet.getInt("quantity");
                            String name = resultSet.getString("name");
                            Double price = resultSet.getDouble("price");
                            String image = resultSet.getString("image");

                            CartProduct cartProduct = new CartProductBuilder(cart_product_id)
                                    .setCartId(id_cart)
                                    .setQuantity(quantity)
                                    .getCartProduct();

                            Product product = new ProductBuilder(product_id)
                                    .setName(name)
                                    .setPrice(price)
                                    .setImage(image)
                                    .getProduct();

                            Cart cart = new CartBuilder(id_cart)
                                    .setUserId(user_id)
                                    .getCart();

                            UserCartProduct userCartProduct = new UserCartProductBuilder()
                                    .setCartProduct(cartProduct)
                                    .setCart(cart)
                                    .setProduct(product)
                                    .getUserCartProduct();

                            userCartsCollection.add(userCartProduct);
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

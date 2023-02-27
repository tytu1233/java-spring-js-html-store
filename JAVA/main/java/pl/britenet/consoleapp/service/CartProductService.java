package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.CartProductBuilder;
import pl.britenet.consoleapp.obj.model.CartProduct;

import java.sql.SQLException;
import java.util.*;

public class CartProductService {
    private final Map<Integer, CartProduct> cartProductMap;
    private final DatabaseService databaseService;

    public CartProductService(DatabaseService databaseService) {
        this.cartProductMap = new HashMap<>();
        this.databaseService = databaseService;
    }

    public Optional<CartProduct> getCartProduct(int id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM cart_product WHERE id_cart_product='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            int cartId = resultSet.getInt("cart_id");
                            int productId = resultSet.getInt("product_id");
                            int quantity = resultSet.getInt("quantity");

                            return new CartProductBuilder(id)
                                    .setCartId(cartId)
                                    .setProductId(productId)
                                    .setQuantity(quantity)
                                    .getCartProduct();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    public Optional<Collection<CartProduct>> getAllProducts() {
        return this.databaseService.performSQL(
                "SELECT * FROM cart_product",
                resultSet -> {
                    try {
                        Collection<CartProduct> cartProductsCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id = resultSet.getInt("id_cart_product");
                            int cartId = resultSet.getInt("cart_id");
                            int productId = resultSet.getInt("product_id");
                            int quantity = resultSet.getInt("quantity");

                            CartProduct cartProduct = new CartProductBuilder(id)
                                    .setCartId(cartId)
                                    .setProductId(productId)
                                    .setQuantity(quantity)
                                    .getCartProduct();

                            cartProductsCollection.add(cartProduct);
                        }
                        return cartProductsCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }

    public Optional<Collection<CartProduct>> getCartProductsByCartId(int cart_id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM cart_product WHERE cart_id='%d'", cart_id),
                resultSet -> {
                    try {
                        Collection<CartProduct> cartProductsCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id = resultSet.getInt("id_cart_product");
                            int cartId = resultSet.getInt("cart_id");
                            int productId = resultSet.getInt("product_id");
                            int quantity = resultSet.getInt("quantity");

                            CartProduct cartProduct = new CartProductBuilder(id)
                                    .setCartId(cartId)
                                    .setProductId(productId)
                                    .setQuantity(quantity)
                                    .getCartProduct();

                            cartProductsCollection.add(cartProduct);
                        }
                        return cartProductsCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }


    public void insertCartProduct(CartProduct cartProduct, String sign) {
        this.databaseService.performDML(
                String.format("INSERT INTO cart_product (cart_id, product_id, quantity) " +
                        "VALUES('%d', '%d', '%d') ON DUPLICATE KEY UPDATE quantity = quantity"+ sign +" 1;",
                        cartProduct.getCartId(),
                        cartProduct.getProductId(),
                        cartProduct.getQuantity())
        );
    }

    public void deleteCartProduct(CartProduct cartProduct) {
        this.databaseService.performDML(
                String.format("DELETE FROM cart_product WHERE id_cart_product='%d'",
                        cartProduct.getId())
        );
    }

    public void updateCartProduct(CartProduct cartProduct) {
        this.databaseService.performDML(
                String.format("UPDATE cart_product SET cart_id='%d', product_id='%d', quantity='%d' WHERE id_cart_product='%d'",
                        cartProduct.getCartId(),
                        cartProduct.getProductId(),
                        cartProduct.getQuantity(),
                        cartProduct.getId())
        );
    }

}

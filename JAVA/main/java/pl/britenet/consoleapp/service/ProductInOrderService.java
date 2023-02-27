package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.ProductInOrderBuilder;
import pl.britenet.consoleapp.obj.model.ProductInOrder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ProductInOrderService {
    private final DatabaseService databaseService;
    public ProductInOrderService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Collection<ProductInOrder>> getProductInOrder(int orderId) {
        return this.databaseService.performSQL(
                String.format("select order_product.Quantity,product.name,product.Price,product.image\n" +
                        "                        from order_product\n" +
                        "                        inner join product ON product.id_product = order_product.Product_id\n" +
                        "                        where order_product.order_id= '%d'", orderId),
                resultSet -> {
                    try {
                        Collection<ProductInOrder> userCartsCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int quantity = resultSet.getInt("quantity");
                            String name = resultSet.getString("name");
                            double price = resultSet.getDouble("price");
                            String image = resultSet.getString("image");

                            ProductInOrder productInOrder = new ProductInOrderBuilder()
                                    .setImage(image)
                                    .setName(name)
                                    .setPrice(price)
                                    .setQuantity(quantity)
                                    .getProductInOrder();

                            userCartsCollection.add(productInOrder);

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

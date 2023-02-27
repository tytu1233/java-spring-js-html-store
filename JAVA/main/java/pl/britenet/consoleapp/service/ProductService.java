package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.ProductBuilder;
import pl.britenet.consoleapp.obj.model.Product;

import java.sql.SQLException;
import java.util.*;

public class ProductService {

    private final DatabaseService databaseService;
    private final Map<Integer, Product> productMap;

    public ProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.productMap = new HashMap<>();
    }

    public Optional<Collection<Product>> getAllProducts() {
        return this.databaseService.performSQL(
                "SELECT * FROM product",
                resultSet -> {
                    try {
                        Collection<Product> productsCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id = resultSet.getInt("id_product");
                            String name = resultSet.getString("name");
                            String description = resultSet.getString("description");
                            Double price = resultSet.getDouble("price");
                            String image = resultSet.getString("image");

                            Product product = new ProductBuilder(id)
                                    .setName(name)
                                    .setDescription(description)
                                    .setPrice(price)
                                    .setImage(image)
                                    .getProduct();

                            productsCollection.add(product);



                        }
                        return productsCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }

    public Optional<Product> getProduct(int id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM product WHERE id_product='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            String name = resultSet.getString("name");
                            String description = resultSet.getString("description");
                            Double price = resultSet.getDouble("price");
                            String image = resultSet.getString("image");

                            return new ProductBuilder(id)
                                    .setName(name)
                                    .setDescription(description)
                                    .setPrice(price)
                                    .setImage(image)
                                    .getProduct();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    public void insertProduct(Product product) {
        this.databaseService.performDML(
                String.format(Locale.US,"INSERT INTO product (name, description, price, image) " +
                                "VALUES ('%s', '%s', '%.2f', '%s')",
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getImage())
        );
    }

    public void updateProduct(Product product) {
        this.databaseService.performDML(
                String.format(Locale.US,"UPDATE product " +
                                "SET name='%s', description='%s', price='%.2f', image='%s' WHERE id_product='%d'",
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getImage(),
                        product.getId())
        );
    }

    public void deleteProduct(Product product) {
        this.databaseService.performDML(
                String.format("DELETE FROM product WHERE id_product='%d'",
                        product.getId())
        );
    }
}

package pl.britenet.consoleapp.service;


import pl.britenet.consoleapp.obj.builder.ProductBuilder;
import pl.britenet.consoleapp.obj.builder.SoldProductBuilder;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.obj.model.SoldProduct;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class SoldProductService {

    private final DatabaseService databaseService;

    public SoldProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Collection<SoldProduct>> getSoldProducts() {
        return this.databaseService.performSQL(
                "SELECT op.product_id, p.name, COUNT(op.product_id) AS sprzedane\n" +
                        "FROM product p\n" +
                        "LEFT JOIN order_product op ON p.id_product=op.product_id\n" +
                        "GROUP BY op.product_id\n" +
                        "ORDER BY COUNT(op.product_id) DESC",
                resultSet -> {
                    try {
                        Collection<SoldProduct> soldProductsCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int id = resultSet.getInt("op.product_id");
                            String name = resultSet.getString("p.name");
                            int productAmount = resultSet.getInt("sprzedane");

                            Product product = new ProductBuilder(id)
                                    .setName(name)
                                    .getProduct();

                            SoldProduct soldProduct = new SoldProductBuilder(id)
                                    .setProduct(product)
                                    .setAmount(productAmount)
                                    .getSoldProduct();

                            soldProductsCollection.add(soldProduct);
                        }
                        return soldProductsCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }
}

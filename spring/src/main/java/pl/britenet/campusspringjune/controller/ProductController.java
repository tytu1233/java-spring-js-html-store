package pl.britenet.campusspringjune.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.service.ProductService;

import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Optional<Collection<Product>> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Optional<Product> getProduct(@PathVariable int productId) {
        return this.productService.getProduct(productId);
    }

    @PostMapping
    public void insertProduct(@RequestBody Product product) {
        this.productService.insertProduct(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        this.productService.updateProduct(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        this.productService.deleteProduct(new Product(productId));
    }
}

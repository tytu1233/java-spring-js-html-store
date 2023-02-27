package pl.britenet.campusspringjune.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.Order;
import pl.britenet.consoleapp.obj.model.ProductInOrder;
import pl.britenet.consoleapp.service.OrderProductService;
import pl.britenet.consoleapp.service.ProductInOrderService;

import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/productInOrder")
public class ProductInOrderController {

    private final ProductInOrderService productInOrderService;

    @Autowired
    public ProductInOrderController(ProductInOrderService productInOrderService) {
        this.productInOrderService = productInOrderService;
    }

    @GetMapping("/{order_id}")
    public Optional<Collection<ProductInOrder>> getAllProductInOrder(@PathVariable int order_id) {
        return this.productInOrderService.getProductInOrder(order_id);
    }
}

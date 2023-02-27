package pl.britenet.campusspringjune.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.service.OrderProductService;

import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/orderproduct")
public class OrderProductController {

    private final OrderProductService orderProductService;

    @Autowired
    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @GetMapping("/user/{cart_id}")
    public Optional<Collection<OrderProduct>> getAllOrderProducts(@PathVariable int cart_id) {
        return this.orderProductService.getAllOrderProducts(cart_id);
    }

    @GetMapping("/{orderProductId}")
    public Optional<OrderProduct> getOrderProduct(@PathVariable int orderProductId) {
        return this.orderProductService.getOrderProduct(orderProductId);
    }

    @PostMapping
    public void insertOrderProduct(@RequestBody OrderProduct orderProduct) {
        this.orderProductService.insertOrderProduct(orderProduct);
    }

    @PutMapping
    public void updateOrderProduct(@RequestBody OrderProduct orderProduct) {
        this.orderProductService.updateOrderProduct(orderProduct);
    }

    @DeleteMapping("/{orderProductId}")
    public void deleteOrderProduct(@PathVariable int orderProductId) {
        this.orderProductService.deleteOrderProduct(new OrderProduct(orderProductId));
    }

}

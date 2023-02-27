package pl.britenet.campusspringjune.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.Order;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.service.OrderService;

import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public Optional<Collection<Order>> getAllOrders() {
        return this.orderService.getAllOrders();
    }

    @GetMapping("/orders/{user_id}")
    public Optional<Collection<Order>> getAllOrders(@PathVariable int user_id) {
        return this.orderService.getAllOrdersByUserId(user_id);
    }

    @GetMapping("/{orderId}")
    public Optional<Order> getOrder(@PathVariable int orderId) {
        return this.orderService.getOrder(orderId);
    }

    @PostMapping
    public void insertOrder(@RequestBody Order order) {
        this.orderService.insertOrder(order);
    }

    @PutMapping
    public void updateOrder(@RequestBody Order order) {
        this.orderService.updateOrder(order);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        this.orderService.deleteOrder(new Order(orderId));
    }

}

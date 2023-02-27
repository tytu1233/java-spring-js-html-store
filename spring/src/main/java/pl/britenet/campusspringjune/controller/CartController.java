package pl.britenet.campusspringjune.controller;

import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.service.CartService;

import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Optional<Collection<Cart>> getAllCarts() {
        return this.cartService.getAllCarts();
    }

    @GetMapping("/{cartId}")
    public Optional<Cart> getCart(@PathVariable int cartId) {
        return this.cartService.getCart(cartId);
    }

    @GetMapping("/user/{userId}")
    public Optional<Cart> getCartByUserId(@PathVariable int userId) {
        return this.cartService.getCartByUserId(userId);
    }

    @PostMapping
    public void insertCart(@RequestBody Cart cart) {
        this.cartService.insertCart(cart);
    }

    @PutMapping
    public void updateCart(@RequestBody Cart cart) {
        this.cartService.updateCart(cart);
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable int cartId) {
        this.cartService.deleteCart(new Cart(cartId));
    }

}

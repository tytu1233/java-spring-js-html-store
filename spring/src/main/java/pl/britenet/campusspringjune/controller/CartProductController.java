package pl.britenet.campusspringjune.controller;

import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.service.CartProductService;

import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cartproduct")
public class CartProductController {

    private final CartProductService cartProductService;

    public CartProductController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }

    @GetMapping
    public Optional<Collection<CartProduct>> getAllCartProducts() {
        return this.cartProductService.getAllProducts();
    }

    @GetMapping("/user/{cartId}")
    public Optional<Collection<CartProduct>> getCartProductsByCartId(@PathVariable int cartId) {
        return this.cartProductService.getCartProductsByCartId(cartId);
    }

    @GetMapping("/{cartProductId}")
    public Optional<CartProduct> getCartProduct(@PathVariable int cartProductId) {
        return this.cartProductService.getCartProduct(cartProductId);
    }

    @PostMapping("/{sign}")
    public void insertCartProduct(@RequestBody CartProduct cartProduct, @PathVariable String sign) {
        this.cartProductService.insertCartProduct(cartProduct, sign);
    }

    @PutMapping
    public void updateCartProduct(@RequestBody CartProduct cartProduct) {
        this.cartProductService.updateCartProduct(cartProduct);
    }

    @DeleteMapping("/{cartProductId}")
    public void deleteCartProduct(@PathVariable int cartProductId) {
        this.cartProductService.deleteCartProduct(new CartProduct(cartProductId));
    }

}

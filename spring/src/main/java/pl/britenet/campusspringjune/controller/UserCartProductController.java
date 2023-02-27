package pl.britenet.campusspringjune.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.UserCartProduct;
import pl.britenet.consoleapp.service.UserCartProductService;

import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/usercartproduct")
public class UserCartProductController {

    private final UserCartProductService userCartProductService;

    @Autowired
    public UserCartProductController(UserCartProductService userCartProductService) {
        this.userCartProductService = userCartProductService;
    }

    @GetMapping("/{userId}")
    public Optional<Collection<UserCartProduct>> getCartProducts(@PathVariable int userId) {
        return this.userCartProductService.getCartProducts(userId);
    }

}

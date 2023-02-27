package pl.britenet.campusspringjune.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.User;
import pl.britenet.consoleapp.service.UserService;

import java.util.Collection;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Optional<Collection<User>> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Optional<User> getUser(@PathVariable int userId) {
        return this.userService.getUser(userId);
    }

    @PostMapping
    public void insertUser(@RequestBody User user) {
        this.userService.insertUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        this.userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId) {
        this.userService.deleteUser(new User(userId));
    }
}

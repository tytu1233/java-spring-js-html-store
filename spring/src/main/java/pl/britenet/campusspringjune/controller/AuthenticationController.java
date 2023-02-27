package pl.britenet.campusspringjune.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campusspringjune.model.AuthenticationResponse;
import pl.britenet.campusspringjune.model.Credentials;
import pl.britenet.campusspringjune.service.AuthenticationService;

import java.util.Collections;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public AuthenticationResponse authenticate(@RequestBody Credentials credentials) {
        return this.authenticationService.authenticate(credentials);
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logoutUser(@RequestBody AuthenticationResponse authenticationResponse) {
        if(this.authenticationService.logout(authenticationResponse)) {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(false);
    }

    @GetMapping
    public ResponseEntity<Object> checkAuthenticatedUser(@RequestHeader("Authorization") String token) {
        if (authenticationService.checkAuthentication(token)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(Collections.singletonMap("response", "pass"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("response", "fail"));
    }

}
package authservice.Controller;

import authservice.Model.AuthenticationRequest;
import authservice.Model.AuthenticationResponse;
import authservice.Model.RegisterRequest;
import authservice.Model.User;
import authservice.Repository.UserRepository;
import authservice.Service.AuthenticationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/authservice")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/validate/{token}")
    public String validateToken(@PathVariable String token) {
        service.validateToken(token);
        return "Valid";
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getMethodName() {
        return ResponseEntity.ok(repo.findAll());
    }

}
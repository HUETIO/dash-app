package com.example.dash_app.Controller;
import com.example.dash_app.Service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login() {
        return authService.authenticate();
    }

    @GetMapping("/suppliers")
    public List<Map<String, String>> getSuppliers(@RequestHeader("Authorization") String token) {
        return authService.getSuppliers(token);
    }
}

package com.example.JWT_RestAPI3.controller;

import com.example.JWT_RestAPI3.model.AdminDeleteUserRequest;
import com.example.JWT_RestAPI3.model.LoginRequest;
import com.example.JWT_RestAPI3.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public LoginRequest register(@RequestBody LoginRequest request) {
        return authService.registerUser(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/username/{token}")
    public String extractUsername(@PathVariable String token) {
        return authService.extractUsername(token);
    }

    @GetMapping("/user")
    public String getUser(Authentication authentication) {
        return "User: " + authentication.getName();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String onlyAdmin(Authentication authentication) {
        return "Admin: " + authentication.getName();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/admin/deleteUser")
    public String deleteUserAsAdmin(@RequestBody AdminDeleteUserRequest request, Authentication authentication) {
        if (authentication.getName().equals(request.getAdminUsername()) && authService.authenticateAdmin(request.getAdminUsername(), request.getAdminPassword())) {
            authService.excluirUsuarioComoAdmin(request.getAdminUsername(), request.getAdminPassword(), request.getUserId());
            return "O usuário com o  ID " + request.getUserId() + " foi deletado pelo Administrador: " + authentication.getName();
        } else {
            throw new RuntimeException("Admin authentication failed");
        }
    }
}

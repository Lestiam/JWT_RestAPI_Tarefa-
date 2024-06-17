package com.example.JWT_RestAPI3.service;

import com.example.JWT_RestAPI3.model.LoginRequest;
import com.example.JWT_RestAPI3.repository.AuthRepository;
import com.example.JWT_RestAPI3.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginRequest registerUser(LoginRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return authRepository.save(request);
    }

    public String login(LoginRequest request) {
        LoginRequest user = authRepository.findByUsername(request.getUsername());

        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return JwtUtil.generateToken(user.getUsername(), user.getRole());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public String extractUsername(String token) {
        return JwtUtil.extractUsername(token);
    }

    public void excluir(String id) {
        authRepository.deleteById(id);
    }

    public boolean authenticateAdmin(String username, String password) {
        LoginRequest admin = authRepository.findByUsername(username);
        return admin != null && admin.getRole().equals("ADMIN") && passwordEncoder.matches(password, admin.getPassword());
    }

    public void excluirUsuarioComoAdmin(String adminUsername, String adminPassword, String userId) {
        if (authenticateAdmin(adminUsername, adminPassword)) {
            excluir(userId);
        } else {
            throw new RuntimeException("Admin authentication failed");
        }
    }
}

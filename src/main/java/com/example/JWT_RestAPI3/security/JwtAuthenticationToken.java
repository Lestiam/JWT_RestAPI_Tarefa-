package com.example.JWT_RestAPI3.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String username;
    private final String role;

    public JwtAuthenticationToken(String username, String role) {
        super(null);
        this.username = username;
        this.role = role;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    public String getRole() {
        return role;
    }
}

package com.restaurant.order.api.service;

import com.restaurant.order.api.dto.request.AuthRequest;
import com.restaurant.order.api.dto.response.AuthResponse;
import com.restaurant.order.api.entity.AppUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse login(AuthRequest request) {
       Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        AuthResponse response = new AuthResponse();
        AppUser user = (AppUser) auth.getPrincipal();
        response.setToken(jwtService.generateToken(user));
        return response;
    }
}

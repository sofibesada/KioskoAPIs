package com.uade.tpo.Marketplace.service;

import com.uade.tpo.Marketplace.controllers.categories.auth.AuthenticationRequest;
import com.uade.tpo.Marketplace.controllers.categories.auth.AuthenticationResponse;
import com.uade.tpo.Marketplace.controllers.categories.auth.RegisterRequest;
import com.uade.tpo.Marketplace.controllers.categories.config.JwtService;
import com.uade.tpo.Marketplace.entity.User;
import com.uade.tpo.Marketplace.repository.users.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        

        // 3) Construir usuario
        User user = User.builder()
                .name(request.getFirstname())         // firstname -> name
                .surname(request.getLastname())       // lastname  -> surname
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(request.getUserType())
                .gender(request.getGender())
                .createdAt(LocalDateTime.now())                       // <-- CLAVE
                .build();

        // 4) Guardar y token
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
package com.uade.tpo.Marketplace.controllers.categories.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(auth -> auth
                                // publico
                                .requestMatchers("/countries/import").permitAll()
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/countries/**").permitAll()

                                // solo CLIENTE
                                .requestMatchers("/cart/**").hasAuthority("CLIENTE")
                                .requestMatchers("/orders/**").hasAuthority("CLIENTE")
                                .requestMatchers("/addresses/**").hasAuthority("CLIENTE")
                                .requestMatchers(HttpMethod.POST, "/orders/**").hasAuthority("CLIENTE")

                                // CLIENTE o ADMIN (pero validar lógica en service/controller)
                                .requestMatchers("/users/**").hasAnyAuthority("CLIENTE","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/invoices/**").hasAnyAuthority("CLIENTE","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/orders/**").hasAnyAuthority("CLIENTE","ADMIN")

                                // solo ADMIN (CRUD productos/categorías)
                                .requestMatchers(HttpMethod.POST, "/products/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/products/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/products/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/categories/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/categories/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/categories/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/countries/import").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/countries/**").permitAll()
                        
                                // el resto si o si autenticado
                                .anyRequest().authenticated()
                        )
                        .sessionManagement(s -> s.sessionCreationPolicy(STATELESS))
                        .authenticationProvider(authenticationProvider)
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                        
                        return http.build();
        }
        @Bean
        public RestTemplate restTemplate() {
                return new RestTemplate();
        }
}

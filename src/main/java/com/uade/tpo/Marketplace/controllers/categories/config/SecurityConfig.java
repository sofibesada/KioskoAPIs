package com.uade.tpo.Marketplace.controllers.categories.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                                .requestMatchers("/products", "/products/*", "/products/*/image").permitAll()
                                .requestMatchers("/categories", "/categories/*").permitAll()
                        
                                // solo USER (admin no compra)
                                .requestMatchers("/cart/**").hasAuthority("CLIENTE")
                                .requestMatchers("/orders/**").hasAuthority("CLIENTE")
                        
                                // usuarios, despues se filtra que los clientes solo puedan modificar SU ID
                                .requestMatchers("/users/**").hasAnyAuthority("CLIENTE","ADMIN")
                        
                                // todo admin
                                .requestMatchers("/products/**").hasAuthority("ADMIN")
                                .requestMatchers("/categories/**").hasAuthority("ADMIN")
                        
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

package com.uade.tpo.Marketplace.controllers.config;

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
        // Auth público
        .requestMatchers("/api/v1/auth/**").permitAll()

        //Público (sin login)
        //    - Productos visibles para cualquiera
        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
        //    - Reviews visibles para cualquiera
        .requestMatchers(HttpMethod.GET, "/reviews/**").permitAll()
        //    - Países visibles para cualquiera
        .requestMatchers(HttpMethod.GET, "/countries/**").permitAll()
        //    - Categorías y métodos de entrega "visibles" para clientes; admin puede todo (incluye GET).
        .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/delivery-methods/**").permitAll()

        // SOLO CLIENTE o ADMIN (visibilidad, no anónimo)
        //    - TypeReceipts: clientes los generan; admin los puede ver
        .requestMatchers(HttpMethod.GET, "/typereceipts/**").hasAnyAuthority("CLIENTE","ADMIN")

        // Admin-only (CRUD de catálogo / mantenimiento)
        .requestMatchers(HttpMethod.POST,   "/products/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.PUT,    "/products/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.PATCH, "/products/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/products/**").hasAuthority("ADMIN")
        // subir imagen de producto
        .requestMatchers(HttpMethod.POST,   "/products/*/upload-image").hasAuthority("ADMIN")

        .requestMatchers(HttpMethod.POST,   "/categories/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.PUT,    "/categories/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/categories/**").hasAuthority("ADMIN")

        .requestMatchers(HttpMethod.POST,   "/delivery-methods/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.PUT,    "/delivery-methods/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/delivery-methods/**").hasAuthority("ADMIN")

        .requestMatchers(HttpMethod.POST,   "/countries/import").hasAuthority("ADMIN")

        // Order States: admin CRUD; clientes (y admin) pueden ver
        .requestMatchers(HttpMethod.GET,    "/order-states/**").hasAnyAuthority("CLIENTE","ADMIN")
        .requestMatchers(HttpMethod.POST,   "/order-states/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.PUT,    "/order-states/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/order-states/**").hasAuthority("ADMIN")

        // Payment Methods: admin CRUD; clientes y admin pueden ver (catálogo de medios disponibles)
        .requestMatchers(HttpMethod.GET,    "/payment-methods/**").hasAnyAuthority("CLIENTE","ADMIN")
        .requestMatchers(HttpMethod.POST,   "/payment-methods/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.PUT,    "/payment-methods/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/payment-methods/**").hasAuthority("ADMIN")

        // Cliente (flujo de compra / datos propios)
        // Orders / OrderDetails / Invoices: clientes crean/modifican; ambos roles pueden ver
        .requestMatchers(HttpMethod.GET,    "/orders/**").hasAnyAuthority("CLIENTE","ADMIN")
        .requestMatchers(HttpMethod.POST,   "/orders/**").hasAuthority("CLIENTE")
        .requestMatchers(HttpMethod.PUT,    "/orders/**").hasAuthority("CLIENTE")
        .requestMatchers(HttpMethod.DELETE, "/orders/**").hasAuthority("CLIENTE")

        .requestMatchers(HttpMethod.GET,    "/order-details/**").hasAnyAuthority("CLIENTE","ADMIN")
        .requestMatchers(HttpMethod.POST,   "/order-details/**").hasAuthority("CLIENTE")
        .requestMatchers(HttpMethod.PUT,    "/order-details/**").hasAuthority("CLIENTE")
        .requestMatchers(HttpMethod.DELETE, "/order-details/**").hasAuthority("CLIENTE")

        .requestMatchers(HttpMethod.GET,    "/invoices/**").hasAnyAuthority("CLIENTE","ADMIN")
        .requestMatchers(HttpMethod.POST,   "/invoices/**").hasAuthority("CLIENTE")
        .requestMatchers(HttpMethod.PUT,    "/invoices/**").hasAuthority("CLIENTE")
        .requestMatchers(HttpMethod.DELETE, "/invoices/**").hasAuthority("CLIENTE")

        // Payments: clientes crean/actualizan/eliminan (pago propio); ambos pueden ver
        .requestMatchers(HttpMethod.GET,    "/payments/**").hasAnyAuthority("CLIENTE","ADMIN")
        .requestMatchers(HttpMethod.POST,   "/payments/**").hasAuthority("CLIENTE")
        .requestMatchers(HttpMethod.PUT,    "/payments/**").hasAuthority("CLIENTE")
        .requestMatchers(HttpMethod.DELETE, "/payments/**").hasAuthority("CLIENTE")

        // Addresses: clientes y admin pueden ver/crear/editar/borrar
        .requestMatchers("/addresses/**").hasAnyAuthority("CLIENTE","ADMIN")

        // Favorites: clientes y admin pueden ver; SOLO clientes crean/eliminan
        .requestMatchers(HttpMethod.GET,    "/favorites/**").hasAnyAuthority("CLIENTE","ADMIN")
        .requestMatchers(HttpMethod.POST,   "/favorites/**").hasAuthority("CLIENTE")
        .requestMatchers(HttpMethod.DELETE, "/favorites/**").hasAuthority("CLIENTE")

        // Reviews: público y admin sólo GET; clientes pueden crear/borrar
        .requestMatchers(HttpMethod.POST,   "/reviews/**").hasAuthority("CLIENTE")
        .requestMatchers(HttpMethod.DELETE, "/reviews/**").hasAuthority("CLIENTE")

        // TypeReceipts: clientes crean/editan/borran
        .requestMatchers(HttpMethod.POST,   "/typereceipts/**").hasAuthority("CLIENTE")
        .requestMatchers(HttpMethod.PUT,    "/typereceipts/**").hasAuthority("CLIENTE")
        .requestMatchers(HttpMethod.DELETE, "/typereceipts/**").hasAuthority("CLIENTE")

        //Users:
        //     Dejamos autenticado y delegamos la fine-grained rule a @PreAuthorize en el controller.
        .requestMatchers("/users/**").authenticated()

        //Cualquier otra cosa: autenticado
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

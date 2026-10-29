package com.upc.demo.security;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // Registro y login públicos
                        .requestMatchers(
                                "/api/usuarios/login",
                                "/api/usuarios/registrar"
                        ).permitAll()

                        // Documentación
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Noticias públicas: resumen
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/noticias/ultimas-seis"
                        ).permitAll()

                        // Resoluciones publicas: resumen
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/resoluciones/ultimas-seis"
                        ).permitAll()

                        // Categorias publicas
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/categorias/todos"
                        ).permitAll()

                        // Noticias completas
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/noticias/todos",
                                "/api/noticias/*"
                        ).authenticated()

                        // Noticias pendientes
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/noticias/noticias-pendientes"
                        ).hasAnyRole("ADMINISTRADOR")

                        // Crear noticias
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/noticias"
                        ).hasAnyRole(
                                "ADMINISTRADOR",
                                "PERSONAL_ESCOLAR"
                        )

                        // Modificar y eliminar noticias:
                        // la autoría se debe validar también en el servicio.
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/noticias/*"
                        ).hasAnyRole("ADMINISTRADOR", "PERSONAL_ESCOLAR")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/noticias/*"
                        ).hasAnyRole("ADMINISTRADOR", "PERSONAL_ESCOLAR")

                        // Resoluciones completas
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/resoluciones/todos",
                                "/api/resoluciones/*"
                        ).authenticated()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/resoluciones"
                        ).hasAnyRole("ADMINISTRADOR", "PERSONAL_ESCOLAR")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/resoluciones/*"
                        ).hasAnyRole("ADMINISTRADOR", "PERSONAL_ESCOLAR")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/resoluciones/*"
                        ).hasAnyRole("ADMINISTRADOR", "PERSONAL_ESCOLAR")

                        // Comentarios: acceso restringido a estos roles.
                        .requestMatchers("/api/comentarios/**")
                        .hasAnyRole(
                                "ADMINISTRADOR",
                                "PERSONAL_ESCOLAR"
                        )

                        // Resto de la gestión de usuarios
                        .requestMatchers("/api/usuarios/**")
                        .hasRole("ADMINISTRADOR")

                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}

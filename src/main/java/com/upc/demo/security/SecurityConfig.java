package com.upc.demo.security;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


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
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(
                List.of(
                        "http://localhost:*",
                        "http://127.0.0.1:*",
                        "https://*.vercel.app",
                        "https://upcfrontend-nine.vercel.app"
                )
        );

        configuration.setAllowedMethods(
                List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CorsConfigurationSource corsConfigurationSource
    ) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource))

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // Las solicitudes preflight de CORS no requieren autenticación.
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Registro y login públicos
                        .requestMatchers(
                                "/api/usuarios/login",
                                "/api/usuarios/registrar"
                        ).permitAll()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/usuarios/crear"
                        ).hasRole("ADMINISTRADOR")

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

                        // Resoluciones públicas: resumen
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/resoluciones/ultimas-seis"
                        ).permitAll()

                        // Categorías públicas
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/categorias/todos",
                                "/api/categoria-noticias/todos"
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
                        ).hasRole("ADMINISTRADOR")

                        // Crear noticias
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/noticias"
                        ).hasAnyRole(
                                "ADMINISTRADOR",
                                "PERSONAL_ESCOLAR"
                        )

                        // Modificar noticias
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/noticias/*"
                        ).hasAnyRole("ADMINISTRADOR", "PERSONAL_ESCOLAR")

                        // Eliminar noticias
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

                        // 1. Permitir creación de comentarios a Estudiantes, Administradores y Personal Escolar
                        .requestMatchers(HttpMethod.POST, "/api/comentarios", "/api/comentarios/**").hasAnyRole(
                                "ESTUDIANTE",
                                "ADMINISTRADOR",
                                "PERSONAL_ESCOLAR"
                        )

                        // 2. Permitir ver comentarios de una resolución (evita el 403 al entrar a ver la resolución)
                        .requestMatchers(HttpMethod.GET, "/api/comentarios/resolucion/**").hasAnyRole(
                                "ESTUDIANTE",
                                "ADMINISTRADOR",
                                "PERSONAL_ESCOLAR"
                        )

                        // 3. Todo el resto de comentarios (aprobar, moderar, obtener todos, eliminar) solo para Administrador y Personal
                        .requestMatchers("/api/comentarios/**").hasAnyRole(
                                "ADMINISTRADOR",
                                "PERSONAL_ESCOLAR"
                        )

                        // Actualización de perfil propio (cualquier usuario autenticado)
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/usuarios/actualizar-perfil/*"
                        ).authenticated()

                        // Gestión de usuarios
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

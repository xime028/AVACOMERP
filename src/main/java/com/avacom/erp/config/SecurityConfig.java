package com.avacom.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Desactivamos CSRF mientras desarrollamos (para evitar problemas con forms simples)
                .csrf(AbstractHttpConfigurer::disable)

                // Dejamos pasar TODO sin autenticación
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // Quitamos el formulario de login por defecto
                .formLogin(AbstractHttpConfigurer::disable)

                // Quitamos también basic auth
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
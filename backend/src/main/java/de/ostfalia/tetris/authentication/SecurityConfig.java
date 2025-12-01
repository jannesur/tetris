package de.ostfalia.tetris.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/v3/api-docs.yaml"
                ).permitAll()

                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/player/**").permitAll()
                .requestMatchers("/authentication/**").permitAll()
                .requestMatchers("/history/**").permitAll()

                .anyRequest().authenticated()
            )

            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())
            )

            .addFilterBefore(
                jwtFilter,
                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}

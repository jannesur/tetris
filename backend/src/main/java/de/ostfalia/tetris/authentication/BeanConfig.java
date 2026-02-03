package de.ostfalia.tetris.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {

    @Bean
    // Stellt einen PasswordEncoder als Spring Bean bereit
    // Wird z.B. zum Hashen und Prüfen von Passwörtern verwendet
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

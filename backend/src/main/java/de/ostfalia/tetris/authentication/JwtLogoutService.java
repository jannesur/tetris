package de.ostfalia.tetris.authentication;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class JwtLogoutService {

    private final Map<String, Instant> loggedOutTokens = new ConcurrentHashMap<>();

    public void logout(String token, Instant expiresAt) {
        if (token == null || token.isBlank() || expiresAt == null) return;
        loggedOutTokens.put(token, expiresAt);
        cleanup();
    }

    public boolean isLoggedOut(String token) {
        cleanup();
        Instant exp = loggedOutTokens.get(token);
        return exp != null && exp.isAfter(Instant.now());
    }

    private void cleanup() {
        Instant now = Instant.now();
        loggedOutTokens.entrySet()
                .removeIf(e -> !e.getValue().isAfter(now));
    }
}


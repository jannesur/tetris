package de.ostfalia.tetris.authentication;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.ostfalia.tetris.player.Player;
import de.ostfalia.tetris.player.PlayerService;

@RestController
@RequestMapping("/authentication")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    private final PlayerService playerService;
    private final JwtService jwtService;
    private final JwtLogoutService jwtLogoutService;

    public AuthenticationController(PlayerService playerService,
                                    JwtService jwtService,
                                    JwtLogoutService jwtLogoutService) {
        this.playerService = playerService;
        this.jwtService = jwtService;
        this.jwtLogoutService = jwtLogoutService;
    }

    @PostMapping("/token")
    public String login(@RequestBody LoginRequest login) {
        Player player = playerService.loadUserByUsername(login.getUsername());

        if (!playerService.checkPassword(login.getPassword(), player.getPassword())) {
            throw new RuntimeException("Invalid login");
        }

        return jwtService.generateToken(player.getUsername());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequest request) {

        if (request == null || request.getToken() == null || request.getToken().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        String token = request.getToken().trim();
        if (token.startsWith("Bearer ")) {
            token = token.substring(7).trim();
        }

        if (!jwtService.isValid(token)) {
            return ResponseEntity.status(401).build();
        }

        Instant expiresAt = jwtService.extractExpiration(token);
        jwtLogoutService.logout(token, expiresAt);

        return ResponseEntity.noContent().build();
    }
}

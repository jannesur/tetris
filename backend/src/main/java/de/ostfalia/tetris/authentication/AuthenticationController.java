package de.ostfalia.tetris.authentication;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.ostfalia.tetris.player.Player;
import de.ostfalia.tetris.player.PlayerService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    private final PlayerService playerService;
    private final JwtService jwtService;


    public AuthenticationController(PlayerService playerService,
                                    JwtService jwtService) {
        this.playerService = playerService;
        this.jwtService = jwtService;

    }


    // Authentifiziert einen Benutzer, prüft das Passwort und erzeugt ein JWT
    // Gibt bei Erfolg Token und Basis-Userdaten zurück, sonst HTTP 401
    @PostMapping("/token")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        Player player = playerService.loadUserByUsername(login.getUsername());

        if (!playerService.checkPassword(login.getPassword(), player.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        String token = jwtService.generateToken(player.getUsername());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "playerId", player.getId(),
                "username", player.getUsername()
        ));
    }
}

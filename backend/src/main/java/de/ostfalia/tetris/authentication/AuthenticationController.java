package de.ostfalia.tetris.authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.ostfalia.tetris.player.Player;
import de.ostfalia.tetris.player.PlayerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/authentification")
public class AuthenticationController {

    private final PlayerService playerService;
    private final JwtService jwtService;

    public AuthenticationController(PlayerService playerService, JwtService jwtService) {
        this.playerService = playerService;
        this.jwtService = jwtService;
    }

    @PostMapping("/token")
    public String login(@RequestBody LoginRequest login) {
        Player player = playerService.loadUserByUsername(login.getUsername());

        if (!playerService.checkPassword(login.getPassword(), player.getPassword())) {
            throw new RuntimeException("Invalid login");
        }

        return jwtService.generateToken(player.getUsername());
    }
}

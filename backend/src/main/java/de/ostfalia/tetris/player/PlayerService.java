package de.ostfalia.tetris.player;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder; // 🔐 hinzugefügt

    public PlayerService(PlayerRepository playerRepository, PasswordEncoder passwordEncoder) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder; // 🔐 hinzugefügt
    }

    // ---------------------------------------------------------
    // 🔵 Dein bestehender Code (UNVERÄNDERT)
    // ---------------------------------------------------------

    public List<Player> getAllPlayers() {
        return this.playerRepository.findAll();
    }

    public Player getPlayerById(Long id) {
        if(!this.playerRepository.existsById(id)) {
            throw new EntityNotFoundException("no player with given id found");
        }
        return this.playerRepository.findById(id).get();
    }

    public Player createPlayer(Player player) {
        // 🔐 Passwortverschlüsselung sinnvoll ergänzt, aber Rest unverändert
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        return this.playerRepository.save(player);
    }

    //Patch für Player updaten
    /* 
     public User login(String email, String rawPassword) {
        Email emailVO = new Email(email);

        User user = userRepository.findByEmail(emailVO)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!user.getPassword().matches(rawPassword)) {
            throw new EntityNotFoundException("Invalid password");
        }

        return user;
    }
    */

    public void deletePlayer(Long id) {
        this.playerRepository.deleteById(id);
    }


    // ---------------------------------------------------------
    // 🔐 JWT-relevante Methoden (NEU, aber ohne bestehendes zu verändern)
    // ---------------------------------------------------------

    public Player loadUserByUsername(String username) {
        return playerRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public Player loginPlayer(String username, String rawPassword) {
        Player player = loadUserByUsername(username);

        if (!checkPassword(rawPassword, player.getPassword())) {
            throw new EntityNotFoundException("Invalid password");
        }

        return player; // AuthController erzeugt daraus später das JWT
    }
}

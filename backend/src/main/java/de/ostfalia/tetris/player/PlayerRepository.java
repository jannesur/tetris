package de.ostfalia.tetris.player;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository <Player, Long> {
    Optional<Player> findByUsername(String username);

    
}

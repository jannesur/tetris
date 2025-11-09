package de.ostfalia.tetris.player;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

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
        return this.playerRepository.save(player);
    }

    //Patch für Player updaten

     public void deletePlayer(Long id) {
        this.playerRepository.deleteById(id);
    }
    
}

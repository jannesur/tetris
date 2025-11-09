package de.ostfalia.tetris.player;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/player")
public class PlayerController {
    
    private final PlayerService playerService;

    public PlayerController (PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return this.playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return this.playerService.getPlayerById(id);
    }
    

	@PostMapping
	public Player createPlayer(@RequestBody Player player) {
		return this.playerService.createPlayer(player);
	}

    //Patch für Player updaten
	//@PatchMapping("/{id}")
	

	@DeleteMapping("/{id}")
	public void deletePlayer(@PathVariable Long id) { 
    }    
		
}

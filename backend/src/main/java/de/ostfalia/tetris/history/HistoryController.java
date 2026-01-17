package de.ostfalia.tetris.history;

import java.util.List;

import de.ostfalia.tetris.player.Player;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "http://localhost:5173")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    public List<HistoryResponse> getMyHistory(Authentication authentication) {
        Player player = (Player) authentication.getPrincipal();
        return historyService.getHistoriesForPlayer(player.getId())
                .stream()
                .map(HistoryResponse::new)
                .toList();
    }

    @GetMapping("/player/{playerId}")
    public List<HistoryResponse> getHistoriesByPlayer(@PathVariable Long playerId) {
        return historyService.getHistoriesForPlayer(playerId)
                .stream()
                .map(HistoryResponse::new)
                .toList();
    }

    @PostMapping
    public HistoryResponse createHistory(@RequestBody HistoryRequest req,
                                         Authentication authentication) {
        Player player = (Player) authentication.getPrincipal();
        History saved = historyService.createHistory(req, player);
        return new HistoryResponse(saved);
    }
}
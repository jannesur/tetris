package de.ostfalia.tetris.history;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

	public HistoryController(HistoryService historyService) {
		this.historyService = historyService;
	}

    @GetMapping
    public List<History> getAllHistories() {
        return this.historyService.getAllHistories();
    }

    @GetMapping("/player/{playerId}")
    public List<History> getHistoriesByPlayer(@PathVariable Long playerId) {
    return historyService.getHistoriesForPlayer(playerId);
    }

    

	@PostMapping
    public History createHistory(@RequestBody HistoryRequest req) {
    return historyService.createHistory(req);
}

	
}

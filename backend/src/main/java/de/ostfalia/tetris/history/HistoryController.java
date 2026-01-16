package de.ostfalia.tetris.history;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = { "http://localhost:5173", "http://localhost:4200" })
public class HistoryController {

    private final HistoryService historyService;

	public HistoryController(HistoryService historyService) {
		this.historyService = historyService;
	}

    @GetMapping
    public List<HistoryResponse> getAllHistories() {
        return this.historyService.getAllHistories()
                .stream()
                .map(HistoryResponse::from)
                .toList();
    }

    @GetMapping("/{playerId}")
    public List<HistoryResponse> getHistoriesByPlayer(@PathVariable Long playerId) {
        return historyService.getHistoriesForPlayer(playerId)
                .stream()
                .map(HistoryResponse::from)
                .toList();
    }

    

	@PostMapping
    public ResponseEntity<HistoryResponse> createHistory(@RequestBody HistoryRequest req) {
        History created = historyService.createHistory(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(HistoryResponse.from(created));
    }

	
}

package org.example.tetrisprototyp.History;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HistoryLoader {

    private final HistoryService historyService;

    public HistoryLoader(HistoryService historyService) {
        this.historyService = historyService;
    }

    public CompletableFuture<List<GameHistoryDTO>> loadHistoryAsync(String jwtToken) {
        return historyService.loadHistoryAsync(jwtToken);
    }
}
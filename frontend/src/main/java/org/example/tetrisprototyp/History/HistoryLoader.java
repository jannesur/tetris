package org.example.tetrisprototyp.History;

import java.util.List;

public class HistoryLoader {

    private final HistoryService historyService;

    public HistoryLoader(HistoryService historyService) {
        this.historyService = historyService;
    }

    public List<GameHistoryDTO> loadHistory(Long playerId, String jwtToken) {
        return historyService.loadHistory(playerId, jwtToken);
    }
}
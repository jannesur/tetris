package de.ostfalia.tetris.history;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import de.ostfalia.tetris.player.Player;
import de.ostfalia.tetris.player.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> getAllHistories() {
        return historyRepository.findAll();
    }

    public History getHistoryById(Long id) {
        return historyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no history with given id found"));
    }

    public List<History> getHistoriesForPlayer(Long playerId) {
        return historyRepository.findByPlayerId(playerId);
    }

    public History createHistory(HistoryRequest req, Player player) {
        History history = new History();
        history.setScore(req.getScore());
        history.setLevel(req.getLevel());
        history.setRowsCleared(req.getRowsCleared());
        history.setDifficulty(req.getDifficulty());
        history.setPlayedAt(parsePlayedAt(req.getPlayedAt()));
        history.setPlayer(player);

        return historyRepository.save(history);
    }

    private LocalDateTime parsePlayedAt(String playedAt) {
        if (playedAt == null || playedAt.isBlank()) {
            return LocalDateTime.now();
        }
        try {
            return LocalDateTime.parse(playedAt);
        } catch (DateTimeParseException ex) {
            return LocalDateTime.now();
        }
    }
}

package de.ostfalia.tetris.history;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import de.ostfalia.tetris.player.Player;
import de.ostfalia.tetris.player.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final PlayerRepository playerRepository;

    public HistoryService(HistoryRepository historyRepository, PlayerRepository playerRepository) {
        this.historyRepository = historyRepository;
        this.playerRepository = playerRepository;
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

    public History createHistory(HistoryRequest req) {
        Player player = resolvePlayer(req);

        History history = new History();
        history.setScore(req.getScore());
        history.setLevel(req.getLevel());
        history.setRowsCleared(req.getRowsCleared());
        history.setDifficulty(req.getDifficulty());
        history.setPlayedAt(resolvePlayedAt(req.getPlayedAt()));
        history.setPlayer(player);

        return historyRepository.save(history);
    }

    private Player resolvePlayer(HistoryRequest req) {
        if (req.getPlayerId() != null) {
            return playerRepository.findById(req.getPlayerId())
                    .orElseThrow(() -> new EntityNotFoundException("Player not found"));
        }

        if (req.getUsername() != null && !req.getUsername().isBlank()) {
            return playerRepository.findByUsername(req.getUsername())
                    .orElseThrow(() -> new EntityNotFoundException("Player not found"));
        }

        throw new EntityNotFoundException("Player not specified");
    }

    private LocalDateTime resolvePlayedAt(String playedAt) {
        if (playedAt != null && !playedAt.isBlank()) {
            return LocalDateTime.parse(playedAt);
        }
        return LocalDateTime.now();
    }
}


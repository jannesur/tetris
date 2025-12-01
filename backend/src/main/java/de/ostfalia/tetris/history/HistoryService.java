package de.ostfalia.tetris.history;

import java.time.LocalDate;
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

        Player player = playerRepository.findById(req.getPlayerId())
                .orElseThrow(() -> new EntityNotFoundException("Player not found"));

        History history = new History();
        history.setScore(req.getScore());
        history.setLevel(req.getLevel());
        history.setHistoryDate(LocalDate.now());
        history.setPlayer(player);

        return historyRepository.save(history);
    }
}


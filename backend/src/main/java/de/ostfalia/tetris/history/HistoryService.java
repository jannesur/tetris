package de.ostfalia.tetris.history;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;

public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> getAllHistories() {
        return this.historyRepository.findAll();
    }

    public History getHistoryById(Long id) {
        if(!this.historyRepository.existsById(id)) {
            throw new EntityNotFoundException("no history with given id found");
        }
        return this.historyRepository.findById(id).get();
    }

    public History createHistory(History history) {
        return this.historyRepository.save(history);
    }

 
}

package org.example.tetrisprototyp.History;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.tetrisprototyp.UserManagement.UserSession;


/**
 * Enthält die Methoden fürs Laden und Speichern der Spielhistorie
 */

public class HistoryService {

    private static final String BASE_URL = "http://localhost:8080/api/history";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public CompletableFuture<Void> saveHistoryAsync(GameHistoryDTO history, String jwtToken) {
        try {
            HistoryRequestDTO payload = new HistoryRequestDTO(
                    history.getScore(),
                    history.getLevel(),
                    history.getRowsCleared(),
                    history.getDifficulty(),
                    history.getPlayedAt()
            );
            String json = mapper.writeValueAsString(payload);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + jwtToken)
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response -> {
                        int code = response.statusCode();
                        if (code != 200 && code != 201) {
                            throw new RuntimeException("Fehler beim Speichern: " + code + " / " + response.body());
                        }
                    });
        } catch (Exception e) {
            CompletableFuture<Void> failed = new CompletableFuture<>();
            failed.completeExceptionally(e);
            return failed;
        }
    }

    public CompletableFuture<List<GameHistoryDTO>> loadHistoryAsync(String jwtToken) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Authorization", "Bearer " + jwtToken)
                    .GET()
                    .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> {
                        if (response.statusCode() != 200) {
                            throw new RuntimeException("Historie laden fehlgeschlagen: " + response.statusCode());
                        }
                        return response.body();
                    })
                    .thenApply(body -> {
                        try {
                            return Arrays.asList(mapper.readValue(body, GameHistoryDTO[].class));
                        } catch (Exception e) {
                            throw new RuntimeException("History-Response konnte nicht geparst werden", e);
                        }
                    });
        } catch (Exception e) {
            CompletableFuture<List<GameHistoryDTO>> failed = new CompletableFuture<>();
            failed.completeExceptionally(e);
            return failed;
        }
    }

}

package org.example.tetrisprototyp.History;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Enthält die Methoden fürs Laden und Speichern der Spielhistorie
 */

public class HistoryService {

    private static final String BASE_URL = "http://localhost:8080/api/history";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public void saveHistory(HistoryRequestDTO history, String jwtToken) {
        try {
            String json = mapper.writeValueAsString(history);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + jwtToken)
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201 || response.statusCode() == 200) {
                System.out.println("Historie erfolgreich gespeichert");
            } else {
                System.err.println("Fehler beim Speichern: " + response.body());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<GameHistoryDTO> loadHistory(Long playerId, String jwtToken) {
        try {
            if (playerId == null) {
                return List.of();
            }

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + playerId))
                    .header("Authorization", "Bearer " + jwtToken)
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("Fehler beim Laden: " + response.body());
                return List.of();
            }

            return Arrays.asList(mapper.readValue(response.body(), GameHistoryDTO[].class));

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

}

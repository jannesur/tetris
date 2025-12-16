package org.example.tetrisprototyp.GameEngine;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HistoryService {

    private static final String BASE_URL = "http://localhost:8080/api/history";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public void saveHistory(GameHistoryDTO history, String jwtToken) {
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
}

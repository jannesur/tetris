package org.example.tetrisprototyp.UserManagement;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthService {

    private static final String BASE_URL = "http://localhost:8080";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    // ---------------------------
    // 🔐 LOGIN
    // ---------------------------
    public String login(String username, String password) {

        try {
            LoginRequestDTO login = new LoginRequestDTO(username, password);
            String json = mapper.writeValueAsString(login);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/authentication/token"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body(); // JWT
            } else {
                throw new RuntimeException("Login fehlgeschlagen");
            }

        } catch (Exception e) {
            throw new RuntimeException("Login Fehler", e);
        }
    }

    // ---------------------------
    // 👤 REGISTRIERUNG
    // ---------------------------
    public PlayerDTO register(String username, String password) {

        try {
            LoginRequestDTO register = new LoginRequestDTO(username, password);
            String json = mapper.writeValueAsString(register);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/player"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 || response.statusCode() == 201) {
                return mapper.readValue(response.body(), PlayerDTO.class);
            } else {
                throw new RuntimeException("Registrierung fehlgeschlagen");
            }

        } catch (Exception e) {
            throw new RuntimeException("Registrierungsfehler", e);
        }
    }
}

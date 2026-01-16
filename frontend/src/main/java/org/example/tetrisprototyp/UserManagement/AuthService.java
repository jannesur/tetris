package org.example.tetrisprototyp.UserManagement;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthService {

    private static final String BASE_URL = "http://localhost:8080/api";
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
                    .uri(URI.create(BASE_URL + "/auth/token"))
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
                    .uri(URI.create(BASE_URL + "/user/register"))
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

    public PlayerDTO loadUser(String username, String jwtToken) {
        try {
            String encoded = URLEncoder.encode(username, StandardCharsets.UTF_8);
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/user/" + encoded))
                    .header("Content-Type", "application/json");

            if (jwtToken != null && !jwtToken.isBlank()) {
                builder.header("Authorization", "Bearer " + jwtToken);
            }

            HttpRequest request = builder.GET().build();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return mapper.readValue(response.body(), PlayerDTO.class);
            }

            throw new RuntimeException("Benutzer konnte nicht geladen werden");
        } catch (Exception e) {
            throw new RuntimeException("Benutzer laden fehlgeschlagen", e);
        }
    }
}

package org.example.tetrisprototyp.UserManagement;

public class LoginResponseDTO {
    public String token;
    public Long playerId;
    public String username;

    public LoginResponseDTO() {}

    public String getToken() {
        return token;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getUsername() {
        return username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


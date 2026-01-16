package org.example.tetrisprototyp.UserManagement;

public class UserSession {

    private static UserSession instance;

    private String username;
    private String jwt;
    private Long playerId;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void login(String username, String jwt, Long playerId) {
        this.username = username;
        this.jwt = jwt;
        this.playerId = playerId;
    }

    public void logout() {
        this.username = null;
        this.jwt = null;
        this.playerId = null;
    }

    public String getJwt() { return jwt; }
    public String getUsername() { return username; }
    public Long getPlayerId() { return playerId; }
}

package org.example.tetrisprototyp.UserManagement;

public class UserSession {

    private static UserSession instance;

    private String username;
    private String jwt;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void login(String username, String jwt) {
        this.username = username;
        this.jwt = jwt;
    }

    public void logout() {
        this.username = null;
        this.jwt = null;
    }

    public String getJwt() { return jwt; }
    public String getUsername() { return username; }
}

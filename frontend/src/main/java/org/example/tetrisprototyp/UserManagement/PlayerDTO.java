package org.example.tetrisprototyp.UserManagement;

public class PlayerDTO {

    private Long id;
    private String username;
    private String registrationDate;

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getRegistrationDate() { return registrationDate; }
    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setRegistrationDate(String registrationDate) { this.registrationDate = registrationDate; }
}

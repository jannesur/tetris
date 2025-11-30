package de.ostfalia.tetris.player;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.ostfalia.tetris.history.History;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    //private String jwt;
    private LocalDate registrationDate;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<History> histories;

    public Player() {

    }

    public Player(Long id, String username, LocalDate registrationDate, List<History> histories) {
        this.id = id;
        this.username = username;
        this.registrationDate = registrationDate;
        this.histories = histories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /* 
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
    */

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

 @Override
public String toString() {
    return "Player [id=" + id + ", username=" + username 
            + ", registrationDate=" + registrationDate + "]";
}

 public String getPassword() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
 }

 public void setPassword(String encode) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setPassword'");
 }



}

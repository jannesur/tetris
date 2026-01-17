# Tetris – JavaFX & Spring Boot

Dieses Projekt ist eine Tetris-Implementierung mit JavaFX-Frontend und Spring-Boot-Backend.  
Neben der Spielmechanik werden Benutzerverwaltung, Authentifizierung und eine Spielhistorie umgesetzt.

---

## Projektübersicht

- JavaFX-Client für Spiel und Benutzeroberfläche  
- Spring Boot REST-Backend  
- JWT-basierte Authentifizierung  
- Speicherung von Spielergebnissen  
- Mehrere Schwierigkeitsgrade und Polyomino-Typen  

---

## Projektstruktur

tetris/
├── backend/ Spring Boot Backend
└── frontend/ JavaFX Client


---

## Technologien

- Java (21+)
- JavaFX
- Spring Boot
- Spring Security (JWT)
- JPA / Hibernate
- H2 Datenbank
- Maven

---

## Starten des Projekts

cd backend
mvn spring-boot:run

Das Backend läuft anschließend unter:
http://localhost:8080

### Frontend starten

cd frontend
mvn javafx:run

---

### Authentifizierung

- Benutzer können sich registrieren und anmelden
- Nach erfolgreichem Login wird ein JWT erzeugt

---

### Spielhistorie
- Die Spielhistorie wird bei Game Over gespeichert
- Anzeige der eigenen Historie im Frontend
- Standardmäßig wird eine H2 In-Memory-Datenbank verwendet

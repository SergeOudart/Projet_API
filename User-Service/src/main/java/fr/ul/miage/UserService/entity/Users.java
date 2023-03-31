package fr.ul.miage.UserService.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor 
public class Users implements Serializable{
    
    private @Id UUID id;
    private String username;
    private String adresse;
    private String email;
    private String nom;
    private String prenom;

    public Users(UUID id, String username, String adresse, String email, String nom, String prenom) {
        this.id = id;
        this.username = username;
        this.adresse = adresse;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}

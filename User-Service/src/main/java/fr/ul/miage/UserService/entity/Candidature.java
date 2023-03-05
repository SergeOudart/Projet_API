package fr.ul.miage.UserService.entity;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor 
public class Candidature {
    
    private @Id @GeneratedValue(generator = "UUID") UUID id;
    private UUID idOffreStage;
    private UUID idUser;
    private Date dateCandidature;
    private String etat;

    public Candidature(UUID idOffreStage, UUID idUser, Date dateCandidature, String etat) {
        this.idOffreStage = idOffreStage;
        this.idUser = idUser;
        this.dateCandidature = dateCandidature;
        this.etat = etat;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdOffreStage() {
        return idOffreStage;
    }

    public void setIdOffreStage(UUID idOffreStage) {
        this.idOffreStage = idOffreStage;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public Date getDateCandidature() {
        return dateCandidature;
    }

    public void setDateCandidature(Date dateCandidature) {
        this.dateCandidature = dateCandidature;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Candidature [dateCandidature=" + dateCandidature + ", etat=" + etat + ", id=" + id + ", idOffreStage="
                + idOffreStage + ", idUser=" + idUser + "]";
    }

}

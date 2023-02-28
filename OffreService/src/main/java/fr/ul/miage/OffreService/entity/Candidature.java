package fr.ul.miage.OffreService.entity;

import java.sql.Date;

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
    
    private @Id @GeneratedValue int id;
    private int idOffreStage;
    private int idUser;
    private Date dateCandidature;
    private String etat;

    public Candidature(int idOffreStage, int idUser, Date dateCandidature, String etat) {
        this.idOffreStage = idOffreStage;
        this.idUser = idUser;
        this.dateCandidature = dateCandidature;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOffreStage() {
        return idOffreStage;
    }

    public void setIdOffreStage(int idOffreStage) {
        this.idOffreStage = idOffreStage;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
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

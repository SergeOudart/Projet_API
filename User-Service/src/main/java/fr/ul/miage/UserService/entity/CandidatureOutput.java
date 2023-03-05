package fr.ul.miage.UserService.entity;

import java.sql.Date;
import java.util.UUID;

import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "candidatures", itemRelation = "candidature")
public class CandidatureOutput {

    private UUID id;
    private UUID idOffreStage;
    private UUID idUser;
    private Date dateCandidature;
    private String etat;

    public CandidatureOutput() {
    }

    public CandidatureOutput(UUID idOffreStage, UUID idUser, Date dateCandidature, String etat) {
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
    
}

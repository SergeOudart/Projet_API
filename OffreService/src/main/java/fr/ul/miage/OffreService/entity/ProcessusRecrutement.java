package fr.ul.miage.OffreService.entity;

import java.io.Serializable;
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
public class ProcessusRecrutement implements Serializable{

    private @Id @GeneratedValue(generator = "UUID") UUID id;
    private UUID idCandidature;
    private int nombreEntretien;
    private String decision;
    private String raison;

    public ProcessusRecrutement(UUID idCandidature, int nombreEntretien, String decision, String raison) {
        this.idCandidature = idCandidature;
        this.nombreEntretien = nombreEntretien;
        this.decision = decision;
        this.raison = raison;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdCandidature() {
        return idCandidature;
    }

    public void setIdCandidature(UUID idCandidature) {
        this.idCandidature = idCandidature;
    }

    public int getNombreEntretien() {
        return nombreEntretien;
    }

    public void setNombreEntretien(int nombreEntretien) {
        this.nombreEntretien = nombreEntretien;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

}

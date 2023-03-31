package fr.ul.miage.OffreService.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class OffreStage implements Serializable{
    
    private @Id UUID id;
    private String nom;
    private String domaine;
    private String descriptionStage;
    private Date datePublication;
    private String niveauEtudesStage;
    private String experienceRequiseStage;
    private Date dateDebutStage;
    private String dureeStage;
    private int salaireStage;
    private String indemnisation;
    private UUID organisationId;
    private String pays;
    private String ville;
    private int codePostal;
    private String rue;
    private Double longitude;
    private Double latitude;
    private String telephone;
    private String url;
    private String statut;

    public OffreStage(UUID uuid, String nom, String domaine, String descriptionStage, Date datePublication, String niveauEtudesStage, String experienceRequiseStage, Date dateDebutStage, String dureeStage, int salaireStage, String indemnisation, UUID organisationId, String pays, String ville, int codePostal, String rue, Double longitude, Double latitude, String telephone, String url) {
        this.id = uuid;
        this.nom = nom;
        this.domaine = domaine;
        this.descriptionStage = descriptionStage;
        this.datePublication = datePublication;
        this.niveauEtudesStage = niveauEtudesStage;
        this.experienceRequiseStage = experienceRequiseStage;
        this.dateDebutStage = dateDebutStage;
        this.dureeStage = dureeStage;
        this.salaireStage = salaireStage;
        this.indemnisation = indemnisation;
        this.organisationId = organisationId;
        this.pays = pays;
        this.ville = ville;
        this.codePostal = codePostal;
        this.rue = rue;
        this.longitude = longitude;
        this.latitude = latitude;
        this.telephone = telephone;
        this.url = url;
        this.statut = "vacante";
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

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getDescriptionStage() {
        return descriptionStage;
    }

    public void setDescriptionStage(String descriptionStage) {
        this.descriptionStage = descriptionStage;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public String getNiveauEtudesStage() {
        return niveauEtudesStage;
    }

    public void setNiveauEtudesStage(String niveauEtudesStage) {
        this.niveauEtudesStage = niveauEtudesStage;
    }

    public String getExperienceRequiseStage() {
        return experienceRequiseStage;
    }

    public void setExperienceRequiseStage(String experienceRequiseStage) {
        this.experienceRequiseStage = experienceRequiseStage;
    }

    public Date getDateDebutStage() {
        return dateDebutStage;
    }

    public void setDateDebutStage(Date dateDebutStage) {
        this.dateDebutStage = dateDebutStage;
    }

    public String getDureeStage() {
        return dureeStage;
    }

    public void setDureeStage(String dureeStage) {
        this.dureeStage = dureeStage;
    }

    public int getSalaireStage() {
        return salaireStage;
    }

    public void setSalaireStage(int salaireStage) {
        this.salaireStage = salaireStage;
    }

    public String getIndemnisation() {
        return indemnisation;
    }

    public void setIndemnisation(String indemnisation) {
        this.indemnisation = indemnisation;
    }

    public UUID getOrganisationId() {
        return this.organisationId;
    }

    public void setOrganisationId(UUID organisationId) {
        this.organisationId = organisationId;
    }

    public String getPays() {
        return this.pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return this.ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCodePostal() {
        return this.codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getRue() {
        return this.rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatut() {
        return this.statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }


    
    
}

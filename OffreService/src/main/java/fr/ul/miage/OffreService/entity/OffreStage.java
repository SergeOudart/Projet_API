package fr.ul.miage.OffreService.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OffreStage implements Serializable{
    
    private @Id @GeneratedValue(generator = "UUID") UUID id;
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
    private int organisationId;
    private String pays;
    private String ville;
    private int codePostal;
    private String rue;
    private Double longitude;
    private Double latitude;
    private String telephone;
    private String url;
    private String paysOrga;
    private String villeOrga;
    private int codePostalOrga;
    private String rueOrga;
    private String emailOrga;
    private String telephoneOrga;
    private String urlOrga;
    private String statut;

    public OffreStage(String nom, String domaine, String descriptionStage, Date datePublication, String niveauEtudesStage, String experienceRequiseStage, Date dateDebutStage, String dureeStage, int salaireStage, String indemnisation, int organisationId, String pays, String ville, int codePostal, String rue, Double longitude, Double latitude, String telephone, String url, String paysOrga, String villeOrga, int codePostalOrga, String rueOrga, String emailOrga, String telephoneOrga, String urlOrga) {
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
        this.paysOrga = paysOrga;
        this.villeOrga = villeOrga;
        this.codePostalOrga = codePostalOrga;
        this.rueOrga = rueOrga;
        this.emailOrga = emailOrga;
        this.telephoneOrga = telephoneOrga;
        this.urlOrga = urlOrga;
        this.statut = "vacante";
    }

    public UUID getId() {
        return id;
    }
    
    
}

package fr.ul.miage.OffreService.entity;

import java.io.Serializable;

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
public class Organisation implements Serializable{
    
    private @Id @GeneratedValue int id;
    private String nom;
    private String adresse;
    private String pays;

    public Organisation(String nom, String adresse, String pays) {
        this.nom = nom;
        this.adresse = adresse;
        this.pays = pays;
    }
}

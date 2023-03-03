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
    private int idOffre;
    private int idCandidature;
    

}

package fr.ul.miage.OffreService.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Organisation implements Serializable{
    
    private @Id @GeneratedValue(generator = "UUID") UUID id;
    private String nomOrga;
    private String paysOrga;
    private String villeOrga;
    private String codePostalOrga;
    private String rueOrga;
    private String emailOrga;
    private String telephoneOrga;
    private String urlOrga;

    public Organisation(String paysOrga, String villeOrga, String codePostalOrga, String rueOrga, String emailOrga, String telephoneOrga, String urlOrga) {
        this.paysOrga = paysOrga;
        this.villeOrga = villeOrga;
        this.codePostalOrga = codePostalOrga;
        this.rueOrga = rueOrga;
        this.emailOrga = emailOrga;
        this.telephoneOrga = telephoneOrga;
        this.urlOrga = urlOrga;
    }
}

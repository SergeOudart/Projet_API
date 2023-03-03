package fr.ul.miage.OffreService.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.ul.miage.OffreService.boundary.*;
import fr.ul.miage.OffreService.boundary.CandidatureAssembler;
import fr.ul.miage.OffreService.entity.OffreStage;
import fr.ul.miage.OffreService.entity.Candidature;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class OffreStageController {

    private final OffreStageRepository or;
    private final OffreStageAssembler oa;
    private final CandidatureRepository cr;
    private final CandidatureAssembler ca;

    OffreStageController(OffreStageRepository or, OffreStageAssembler oa, CandidatureRepository cr, CandidatureAssembler ca){
        this.or = or;
        this.oa = oa;
        this.cr = cr;
        this.ca = ca;
    }

    @GetMapping("/Offres")
    public ResponseEntity<?> getAllOffres(){
        return ResponseEntity.ok(oa.toCollectionModel(or.findAll()));
    }
    
    @GetMapping("/Offres/{id}")
    public ResponseEntity<?> getOffreById(@PathVariable("id") UUID uuid){
        return ResponseEntity.ok(oa.toModel(or.findById(uuid).get()));
    }

    @GetMapping("/filtre/{filtre}")
    public ResponseEntity<?> getOffreByFiltre(@PathVariable("filtre") String filtre){
        if (filtre.contains("domaine=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findByDomaine(filtre.replace("domaine=", ""))));
        } else if (filtre.contains("niveau=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findByNiveauEtudesStage(filtre.replace("niveau=", ""))));
        } else if (filtre.contains("experience=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findByExperienceRequiseStage(filtre.replace("experience=", ""))));
        } else if (filtre.contains("pays=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findByPays(filtre.replace("pays=", ""))));
        } else if (filtre.contains("ville=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findByVille(filtre.replace("ville=", ""))));
        } else if (filtre.contains("paysOrga=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findByPaysOrga(filtre.replace("paysOrga=", ""))));
        } else if (filtre.contains("villeOrga=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findByVilleOrga(filtre.replace("villeOrga=", ""))));
        } else if (filtre.contains("nom=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findByNom(filtre.replace("nom=", ""))));
        } else if (filtre.contains("date=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findByDatePublication(filtre.replace("date=", ""))));
        } else if (filtre.contains("dateDebut=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findByDateDebutStage(filtre.replace("dateDebut=", ""))));
        } else if (filtre.contains("duree=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findByDureeStage(filtre.replace("duree=", ""))));
        } else if (filtre.contains("salaire=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findBySalaireStage(filtre.replace("salaire=", ""))));
        } else if (filtre.contains("indemnisation=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findByIndemnisation(filtre.replace("indemnisation=", ""))));
        } else if (filtre.contains("organisationId=")) {
            return ResponseEntity.ok(oa.toCollectionModel(or.findByOrganisationId(filtre.replace("organisationId=", ""))));
        }
        return ResponseEntity.ok(oa.toCollectionModel(or.findAll()));
    }

    @PostMapping("/Offres")
    @Transactional
    public ResponseEntity<?> save(@RequestBody OffreStage offreStage){
        OffreStage toSave = new OffreStage(
            offreStage.getNom(),
            offreStage.getDomaine(),
            offreStage.getDescriptionStage(),
            offreStage.getDatePublication(),
            offreStage.getNiveauEtudesStage(),
            offreStage.getExperienceRequiseStage(),
            offreStage.getDateDebutStage(),
            offreStage.getDureeStage(),
            offreStage.getSalaireStage(),
            offreStage.getIndemnisation(),
            offreStage.getOrganisationId(),
            offreStage.getPays(),
            offreStage.getVille(),
            offreStage.getCodePostal(),
            offreStage.getRue(),
            offreStage.getLongitude(),
            offreStage.getLatitude(),
            offreStage.getTelephone(),
            offreStage.getUrl(),
            offreStage.getPaysOrga(),
            offreStage.getVilleOrga(),
            offreStage.getCodePostalOrga(),
            offreStage.getRueOrga(),
            offreStage.getEmailOrga(),
            offreStage.getTelephoneOrga(),
            offreStage.getUrlOrga()
        );
        OffreStage saved = or.save(toSave);
        URI location = linkTo(OffreStageController.class).slash(saved.getId()).toUri();
        return ResponseEntity.ok(location);
    }

    @PostMapping("/Offres/{id}")
    @Transactional
    public ResponseEntity<?> save(@RequestBody Candidature candidature, @PathVariable("id") UUID id){
        Candidature toSave = new Candidature(
            id,
            candidature.getIdUser(),
            candidature.getDateCandidature(),
            "En attente"
        );
        Candidature saved = cr.save(toSave);
        URI location = linkTo(OffreStageController.class).slash(saved.getId()).toUri();
        return ResponseEntity.ok(location);
    }

    @GetMapping("/Candidatures/{id}/candidature")
    public ResponseEntity<?> getCandidatureById(@PathVariable("id") UUID uuid){
        return ResponseEntity.ok(ca.toCollectionModel(cr.findByIdUser(uuid)));
    }

    @GetMapping("/Candidatures") // A RETIRER
    public ResponseEntity<?> getAllCandidatures(){
        return ResponseEntity.ok(ca.toCollectionModel(cr.findAll()));
    } 
    
}

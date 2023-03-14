package fr.ul.miage.OffreService.controllers;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.ul.miage.OffreService.boundary.*;
import fr.ul.miage.OffreService.boundary.CandidatureAssembler;
import fr.ul.miage.OffreService.entity.OffreStage;
import fr.ul.miage.OffreService.entity.Candidature;
import fr.ul.miage.OffreService.entity.ProcessusRecrutement;
import fr.ul.miage.OffreService.entity.Users;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class OffreStageController {

    private final OffreStageRepository or;
    private final OffreStageAssembler oa;
    private final CandidatureRepository cr;
    private final CandidatureAssembler ca;
    private final ProcessusRecrutementRepository pr;
    private final RestTemplate restTemplate;

    OffreStageController(OffreStageRepository or, OffreStageAssembler oa, CandidatureRepository cr, CandidatureAssembler ca, ProcessusRecrutementRepository pr, RestTemplate restTemplate){
        this.or = or;
        this.oa = oa;
        this.cr = cr;
        this.ca = ca;
        this.pr = pr;
        this.restTemplate = restTemplate;
    }


    //Récuperer toutes les offres
    @GetMapping("/Offres")
    public ResponseEntity<?> getAllOffres(){
        return ResponseEntity.ok(oa.toCollectionModel(or.findAll()));
    }
    
    //Récuperer une offre par son id
    @GetMapping("/Offres/{id}")
    public ResponseEntity<?> getOffreById(@PathVariable("id") UUID uuid){
        return ResponseEntity.ok(oa.toModel(or.findById(uuid).get()));
    }


    //Récuperer les offres par filtre
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

    //Créer une offre
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

    //Poster une candidature
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

    //Récuperer les candidatures d'une personne
    @GetMapping("/Candidatures/{id}/candidature")
    public ResponseEntity<?> getCandidatureById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(ca.toCollectionModel(cr.findByIdUser(id)));
    }

    //Poster un processus de recrutement
    @PostMapping("/Candidatures/{id}/candidature")
    public ResponseEntity<?> postProcessusRecrutement(@PathVariable("id") UUID uuid, @RequestBody ProcessusRecrutement processus){
        ProcessusRecrutement toSave = new ProcessusRecrutement(
            processus.getIdCandidature(),
            processus.getNombreEntretien(),
            processus.getDecision(),
            processus.getRaison()
        );
        ProcessusRecrutement saved = pr.save(toSave);

        Candidature toUpdate = cr.findById(uuid).get();
        toUpdate.setEtat("Accepté");
        cr.save(toUpdate);
        URI location = linkTo(OffreStageController.class).slash(saved.getId()).toUri();
        return ResponseEntity.ok(location);
    }

    @GetMapping("/Offres/{id}/users")
    public ResponseEntity<?> getCandidaturesByOffre(@PathVariable("id") UUID uuid){
        return ResponseEntity.ok(ca.toCollectionModel(cr.findByIdOffreStage(uuid)));
    }

    @PutMapping("Offres/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID uuid, @RequestBody OffreStage offreStage){
        OffreStage toUpdate = or.findById(uuid).get();
        toUpdate.setNom(offreStage.getNom());
        toUpdate.setDomaine(offreStage.getDomaine());
        toUpdate.setDescriptionStage(offreStage.getDescriptionStage());
        toUpdate.setDatePublication(offreStage.getDatePublication());
        toUpdate.setNiveauEtudesStage(offreStage.getNiveauEtudesStage());
        toUpdate.setExperienceRequiseStage(offreStage.getExperienceRequiseStage());
        toUpdate.setDateDebutStage(offreStage.getDateDebutStage());
        toUpdate.setDureeStage(offreStage.getDureeStage());
        toUpdate.setSalaireStage(offreStage.getSalaireStage());
        toUpdate.setIndemnisation(offreStage.getIndemnisation());
        toUpdate.setOrganisationId(offreStage.getOrganisationId());
        toUpdate.setPays(offreStage.getPays());
        toUpdate.setVille(offreStage.getVille());
        toUpdate.setCodePostal(offreStage.getCodePostal());
        toUpdate.setRue(offreStage.getRue());
        toUpdate.setLongitude(offreStage.getLongitude());
        toUpdate.setLatitude(offreStage.getLatitude());
        toUpdate.setTelephone(offreStage.getTelephone());
        toUpdate.setUrl(offreStage.getUrl());
        toUpdate.setPaysOrga(offreStage.getPaysOrga());
        toUpdate.setVilleOrga(offreStage.getVilleOrga());
        toUpdate.setCodePostalOrga(offreStage.getCodePostalOrga());
        toUpdate.setRueOrga(offreStage.getRueOrga());
        toUpdate.setEmailOrga(offreStage.getEmailOrga());
        toUpdate.setTelephoneOrga(offreStage.getTelephoneOrga());
        toUpdate.setUrlOrga(offreStage.getUrlOrga());
        or.save(toUpdate);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("Offres/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID uuid){
        Optional<?> toDelete = or.findById(uuid);
        if(toDelete.isPresent()){
            OffreStage offreStage = (OffreStage) toDelete.get();
            or.delete(offreStage);

            return ResponseEntity.ok("Offre supprimée");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/Candidatures/{idUser}/{idCandidature}")
    public ResponseEntity<?> getCandidatureByUser(@PathVariable("idUser") UUID idUser, @PathVariable("idCandidature") UUID idCandidature){
        Optional<?> toDelete = cr.findById(idCandidature);
        if(toDelete.isPresent()) {
            return ResponseEntity.ok(toDelete.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/Candidatures/{idCandidature}")
    public ResponseEntity<?> getStatutForCandidature(@PathVariable("idCandidature") UUID idCandidature){
        
        return ResponseEntity.ok(ca.toCollection)

    }
        
}
    

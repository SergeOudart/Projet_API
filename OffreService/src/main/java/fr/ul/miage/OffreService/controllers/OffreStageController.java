package fr.ul.miage.OffreService.controllers;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
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

import fr.ul.miage.OffreService.boundary.Assemblers.CandidatureAssembler;
import fr.ul.miage.OffreService.boundary.Assemblers.OffreStageAssembler;
import fr.ul.miage.OffreService.boundary.Assemblers.ProcessusRecrutementAssembler;
import fr.ul.miage.OffreService.boundary.Repository.CandidatureRepository;
import fr.ul.miage.OffreService.boundary.Repository.OffreStageRepository;
import fr.ul.miage.OffreService.boundary.Repository.OrganisationRepository;
import fr.ul.miage.OffreService.boundary.Repository.ProcessusRecrutementRepository;
import fr.ul.miage.OffreService.entity.OffreStage;
import fr.ul.miage.OffreService.entity.Organisation;
import fr.ul.miage.OffreService.entity.Candidature;
import fr.ul.miage.OffreService.entity.ProcessusRecrutement;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class OffreStageController {

    @Autowired
    private final OffreStageRepository or;
    @Autowired
    private final OffreStageAssembler oa;
    @Autowired
    private final CandidatureRepository cr;
    @Autowired
    private final CandidatureAssembler ca;
    @Autowired
    private final OrganisationRepository orgar;
    @Autowired
    private final ProcessusRecrutementRepository pr;
    @Autowired
    private final ProcessusRecrutementAssembler pra;

    OffreStageController(OffreStageRepository or, OffreStageAssembler oa, CandidatureRepository cr, CandidatureAssembler ca, ProcessusRecrutementRepository pr, OrganisationRepository orgar, ProcessusRecrutementAssembler pra){
        this.or = or;
        this.oa = oa;
        this.cr = cr;
        this.ca = ca;
        this.pr = pr;
        this.orgar = orgar;
        this.pra = pra;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    //Récuperer toutes les offres
    @GetMapping("/Offres")
    public ResponseEntity<?> getAllOffres(){
        return ResponseEntity.ok(oa.toCollectionModel(or.findAll()));
    }
    
    //Récuperer une offre par son id
    @GetMapping("/Offres/{id}")
    public ResponseEntity<?> getOffreById(@PathVariable("id") UUID uuid){
        Optional<OffreStage> offre = or.findById(uuid);
        if (offre.isPresent()) {
            return ResponseEntity.ok(oa.toModel(offre.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // Ajouter une organisation
    @PostMapping("/Organisations")
    public ResponseEntity<?> getOrganisation(@RequestBody Organisation orga){
        return ResponseEntity.ok(orgar.save(orga));
    }

    //Récuperer toutes les organisations
    @GetMapping("/Organisations")
    public ResponseEntity<?> getAllOrganisations(){
        return ResponseEntity.ok(orgar.findAll());
    }

    //Récuperer les offres par filtre
    @GetMapping("/filtre/{filtre}")
    public ResponseEntity<?> getOffreByFiltre(@PathVariable("filtre") String filtre){
        if (filtre.contains("domaine=")) {
            Iterable<? extends OffreStage> offre = or.findByDomaine(filtre.replace("domaine=", ""));
            if (offre.iterator().hasNext()) {
                return ResponseEntity.ok(oa.toCollectionModel(offre));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else if (filtre.contains("niveau=")) {
            Iterable<? extends OffreStage> offre = or.findByNiveauEtudesStage(filtre.replace("niveau=", ""));
            if (offre.iterator().hasNext()) {
                return ResponseEntity.ok(oa.toCollectionModel(offre));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else if (filtre.contains("experience=")) {

            Iterable<? extends OffreStage> offre = or.findByExperienceRequiseStage(filtre.replace("experience=", ""));
            if (offre.iterator().hasNext()) {
                return ResponseEntity.ok(oa.toCollectionModel(offre));
            } else {
                return ResponseEntity.notFound().build();
            }

        } else if (filtre.contains("pays=")) {

            Iterable<? extends OffreStage> offre = or.findByPays(filtre.replace("pays=", ""));
            if (offre.iterator().hasNext()) {
                return ResponseEntity.ok(oa.toCollectionModel(offre));
            } else {
                return ResponseEntity.notFound().build();
            }

        } else if (filtre.contains("ville=")) {

            Iterable<? extends OffreStage> offre = or.findByVille(filtre.replace("ville=", ""));
            if (offre.iterator().hasNext()) {
                return ResponseEntity.ok(oa.toCollectionModel(offre));
            } else {
                return ResponseEntity.notFound().build();
            }

        } else if (filtre.contains("paysOrga=")) {

            Iterable<? extends OffreStage> offre = or.findByPays(filtre.replace("paysOrga=", ""));
            if (offre.iterator().hasNext()) {
                return ResponseEntity.ok(oa.toCollectionModel(offre));
            } else {
                return ResponseEntity.notFound().build();
            }

        } else if (filtre.contains("villeOrga=")) {
            Iterable<Organisation> orga = orgar.findByVilleOrga(filtre.replace("villeOrga=", ""));
            return ResponseEntity.ok(oa.toCollectionModel(or.findByOrganisationId(orga.iterator().next().getId())));
        } else if (filtre.contains("nomOrga=")) {
            Iterable<Organisation> orga = orgar.findByNomOrga(filtre.replace("nomOrga=", ""));
            return ResponseEntity.ok(oa.toCollectionModel(or.findByOrganisationId(orga.iterator().next().getId())));
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
        }
        return ResponseEntity.ok(oa.toCollectionModel(or.findAll()));
    }

    //Créer une offre
    @PostMapping("/Offres")
    @Transactional
    public ResponseEntity<?> save(@RequestBody OffreStage offreStage){
        try {
            OffreStage saved = or.save(
                new OffreStage(
                    UUID.randomUUID(),
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
                    offreStage.getUrl()
                )
            );

            System.out.println(saved.toString());
            ResponseEntity.ok(oa.toModel(or.save(saved)));
            return ResponseEntity.status(HttpStatus.CREATED).body(oa.toModel(or.save(saved)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
            uuid,
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

    //Récupérer les candidatures d'une offre
    @GetMapping("/Offres/{id}/users")
    public ResponseEntity<?> getCandidaturesByOffre(@PathVariable("id") UUID uuid){
        return ResponseEntity.ok(ca.toCollectionModel(cr.findByIdOffreStage(uuid)));
    }

    //Mettre à jour une offre
    @PutMapping("Offres/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID uuid, @RequestBody OffreStage offreStage){
        OffreStage toUpdate = or.findById(uuid).get();
        if (toUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        if (offreStage.getNom() != null) {
            toUpdate.setNom(offreStage.getNom());
        }
        if (offreStage.getDomaine() != null) {
            toUpdate.setDomaine(offreStage.getDomaine());
        }
        if (offreStage.getDescriptionStage() != null) {
            toUpdate.setDescriptionStage(offreStage.getDescriptionStage());
        }
        if (offreStage.getDatePublication() != null) {
            toUpdate.setDatePublication(offreStage.getDatePublication());
        }
        if (offreStage.getNiveauEtudesStage() != null) {
            toUpdate.setNiveauEtudesStage(offreStage.getNiveauEtudesStage());
        }
        if (offreStage.getExperienceRequiseStage() != null) {
            toUpdate.setExperienceRequiseStage(offreStage.getExperienceRequiseStage());
        }
        if (offreStage.getDateDebutStage() != null) {
            toUpdate.setDateDebutStage(offreStage.getDateDebutStage());
        }
        if (offreStage.getDureeStage() != null) {
            toUpdate.setDureeStage(offreStage.getDureeStage());
        }
        if (offreStage.getSalaireStage() != 0) {
            toUpdate.setSalaireStage(offreStage.getSalaireStage());
        }
        if (offreStage.getIndemnisation() != null) {
            toUpdate.setIndemnisation(offreStage.getIndemnisation());
        }
        if (offreStage.getOrganisationId() != null) {
            toUpdate.setOrganisationId(offreStage.getOrganisationId());
        }
        if (offreStage.getPays() != null) {
            toUpdate.setPays(offreStage.getPays());
        }
        if (offreStage.getVille() != null) {
            toUpdate.setVille(offreStage.getVille());
        }
        if (offreStage.getCodePostal() != 0) {
            toUpdate.setCodePostal(offreStage.getCodePostal());
        }
        if (offreStage.getRue() != null) {
            toUpdate.setRue(offreStage.getRue());
        }
        if (offreStage.getLongitude() != 0) {
            toUpdate.setLongitude(offreStage.getLongitude());
        }
        if (offreStage.getLatitude() != 0) {
            toUpdate.setLatitude(offreStage.getLatitude());
        }
        if (offreStage.getTelephone() != null) {
            toUpdate.setTelephone(offreStage.getTelephone());
        }
        if (offreStage.getUrl() != null) {
            toUpdate.setUrl(offreStage.getUrl());
        }
        if (offreStage.getStatut() != null) {
            toUpdate.setStatut(offreStage.getStatut());
        }
        or.save(toUpdate);

        return ResponseEntity.ok(oa.toModel(toUpdate));
    }

    //Supprimer une offre
    @DeleteMapping("Offres/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID uuid){
        Optional<?> toDelete = or.findById(uuid);
        if(toDelete.isPresent()){
            OffreStage offreStage = (OffreStage) toDelete.get();
            or.delete(offreStage);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    //Supprimer une candidature
    @DeleteMapping("/Candidatures/{idUser}/{idCandidature}")
    public ResponseEntity<?> getCandidatureByUser(@PathVariable("idUser") UUID idUser, @PathVariable("idCandidature") UUID idCandidature){
        Optional<?> toDelete = cr.findById(idCandidature);
        if(toDelete.isPresent()) {
            return ResponseEntity.ok(toDelete.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    //Récupérer une candidature
    @GetMapping("/Candidatures/{idCandidature}")
    public ResponseEntity<?> getStatutForCandidature(@PathVariable("idCandidature") UUID idCandidature){
        return ResponseEntity.ok(ca.toModel(cr.findById(idCandidature).get()));
    }

    //Récupérer les processus de recrutement d'une candidature
    @GetMapping("/Candidatures/{idCandidature}/processus")
    public ResponseEntity<?> getProcessusForCandidature(@PathVariable("idCandidature") UUID idCandidature){
        return ResponseEntity.ok(pra.toCollectionModel(pr.findByIdCandidature(idCandidature)));
    }
        
}
    

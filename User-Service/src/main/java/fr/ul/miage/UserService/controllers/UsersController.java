package fr.ul.miage.UserService.controllers;

import java.util.UUID;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import fr.ul.miage.UserService.boundary.UsersAssembler;
import fr.ul.miage.UserService.boundary.UsersRepository;
import fr.ul.miage.UserService.boundary.CandidatureRepository;
import fr.ul.miage.UserService.entity.Candidature;

@RestController
public class UsersController {

    private final UsersRepository ur;
    private final UsersAssembler ua;
    private final CandidatureRepository cr;
    private final RestTemplate restTemplate;


    UsersController(UsersRepository ur, UsersAssembler ua, RestTemplate restTemplate, CandidatureRepository cr){
        this.ur = ur;
        this.ua = ua;
        this.restTemplate = restTemplate;
        this.cr = cr;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @GetMapping("/Users")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(ua.toCollectionModel(ur.findAll()));
    }

    // Récupérer user par id
    @GetMapping("/Users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") UUID uuid){
        return ResponseEntity.ok(ua.toModel(ur.findById(uuid).get()));
    }

    // Récupérer user par login
    @GetMapping("/Users/login/{username}")
    public ResponseEntity<?> getUserByLogin(@PathVariable("username") String username){
        return ResponseEntity.ok(ua.toModel(ur.findByUsername(username).get()));
    }

    // Get candidatures de l'utilisateur
    @GetMapping("/Users/{username}/candidatures")
    public ResponseEntity<?> getCandidaturesByUser(@PathVariable("username") String username){
        UUID id = ur.findByUsername(username).get().getId();
        String url = "http://restservice-Offre:8080/Candidatures/{id}/candidature";

        ResponseEntity<?> response = restTemplate.getForEntity(url, CollectionModel.class, id);

        return ResponseEntity.ok(response.getBody());
    }

    //Supprimer la candidature de l'utilisateur
    @DeleteMapping("/Users/{username}/candidatures/{idCandidature}")
    public ResponseEntity<?> deleteCandidature(@PathVariable("username") String username, @PathVariable("idCandidature") UUID idCandidature){
        UUID id = ur.findByUsername(username).get().getId();
        String url = "http://restservice-Offre:8080/Candidatures/{idUser}/{idCandidature}";

        ResponseEntity<Candidature> response = restTemplate.getForEntity(url, Candidature.class, id, idCandidature);

        Candidature c = response.getBody();

        cr.delete(c);

        String message = String.format("Candidature [%s] supprimée", idCandidature);

        return ResponseEntity.ok(message);
    }

    // Get statut offre
    @GetMapping("/Users/{username}/candidatures/{idCandidature}")
    public ResponseEntity<?> getStatutOffre(@PathVariable("username") String username, @PathVariable("idCandidature") UUID idCandidature) {
        String url = "http://restservice-Offre:8080/Candidatures/{idCandidature}";

        ResponseEntity<Candidature> response = restTemplate.getForEntity(url, Candidature.class, idCandidature);

        Candidature c = response.getBody();

        return ResponseEntity.ok(c.getEtat());
    }
}

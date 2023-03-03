package fr.ul.miage.UserService.controllers;

import java.util.UUID;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.ul.miage.UserService.entity.CandidatureBean;
import fr.ul.miage.UserService.boundary.UsersAssembler;
import fr.ul.miage.UserService.boundary.UsersRepository;

@RestController
public class UsersController {

    private final UsersRepository ur;
    private final UsersAssembler ua;
    RestTemplate restTemplate;
    LoadBalancerClientFactory clientFactory;

    UsersController(UsersRepository ur, UsersAssembler ua, RestTemplate rt, LoadBalancerClientFactory cf){
        this.ur = ur;
        this.ua = ua;
        this.restTemplate = rt;
        this.clientFactory = cf;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @GetMapping("/Users")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(ua.toCollectionModel(ur.findAll()));
    }

    @GetMapping("/Users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") UUID uuid){
        return ResponseEntity.ok(ua.toModel(ur.findById(uuid).get()));
    }

    @GetMapping("/Users/login/{username}")
    public ResponseEntity<?> getUserByLogin(@PathVariable("username") String username){
        return ResponseEntity.ok(ua.toModel(ur.findByUsername(username).get()));
    }

    @GetMapping("/Users/{username}/candidatures")
    public ResponseEntity<?> getCandidaturesByUser(@PathVariable("username") String username){
        RoundRobinLoadBalancer lb = clientFactory.getInstance("offre-service", RoundRobinLoadBalancer.class);
        ServiceInstance instance = lb.choose().block().getServer();
        UUID id = ur.findByUsername(username).get().getId();
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/Candidatures/{id}/candidature";

        CandidatureBean response = new RestTemplate().getForObject(url, CandidatureBean.class, id);
        // No servers available for service: OffreService
        return ResponseEntity.ok(null);
    }
    

    
}

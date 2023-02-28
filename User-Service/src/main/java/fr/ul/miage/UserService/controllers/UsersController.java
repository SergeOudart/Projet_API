package fr.ul.miage.UserService.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.ul.miage.UserService.entity.Users;
import fr.ul.miage.UserService.boundary.UsersAssembler;
import fr.ul.miage.UserService.boundary.UsersRepository;

@RestController
public class UsersController {

    private final UsersRepository ur;
    private final UsersAssembler ua;

    UsersController(UsersRepository ur, UsersAssembler ua){
        this.ur = ur;
        this.ua = ua;
    }

    @GetMapping("/Users")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(ua.toCollectionModel(ur.findAll()));
    }

    @GetMapping("/Users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int l) {
        return ResponseEntity.ok(ua.toModel(ur.findById(l).get()));
    }

    
}

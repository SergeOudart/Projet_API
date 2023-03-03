package fr.ul.miage.OffreService.boundary;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ul.miage.OffreService.entity.Candidature;

public interface CandidatureRepository extends JpaRepository<Candidature,Integer>{

    Iterable<? extends Candidature> findByIdUser(UUID idUser);
    
}

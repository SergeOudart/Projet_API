package fr.ul.miage.UserService.boundary;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ul.miage.UserService.entity.Candidature;

public interface CandidatureRepository extends JpaRepository<Candidature,Integer>{

    Iterable<? extends Candidature> findByIdUser(UUID idUser);
    Iterable<? extends Candidature> findByIdOffreStage(UUID idOffre);
    Optional<? extends Candidature> findById(UUID id);
    
}

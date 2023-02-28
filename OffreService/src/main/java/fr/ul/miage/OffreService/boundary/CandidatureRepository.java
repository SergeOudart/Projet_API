package fr.ul.miage.OffreService.boundary;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ul.miage.OffreService.entity.Candidature;

public interface CandidatureRepository extends JpaRepository<Candidature,Integer>{
    
}

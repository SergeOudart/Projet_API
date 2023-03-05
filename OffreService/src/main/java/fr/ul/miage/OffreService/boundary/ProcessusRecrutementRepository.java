package fr.ul.miage.OffreService.boundary;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ul.miage.OffreService.entity.ProcessusRecrutement;

public interface ProcessusRecrutementRepository extends JpaRepository<ProcessusRecrutement,UUID>{
    
}

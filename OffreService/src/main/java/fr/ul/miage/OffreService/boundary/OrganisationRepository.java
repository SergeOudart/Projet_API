package fr.ul.miage.OffreService.boundary;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ul.miage.OffreService.entity.Organisation;

public interface OrganisationRepository extends JpaRepository<Organisation,Integer> {
    
}

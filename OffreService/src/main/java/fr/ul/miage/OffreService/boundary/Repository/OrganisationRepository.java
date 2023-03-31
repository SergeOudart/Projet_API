package fr.ul.miage.OffreService.boundary.Repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ul.miage.OffreService.entity.Organisation;

public interface OrganisationRepository extends JpaRepository<Organisation,UUID> {
    Iterable<Organisation> findByVilleOrga(String villeOrga);
    Iterable<Organisation> findByPaysOrga(String paysOrga);
    Iterable<Organisation> findByNomOrga(String nomOrga);
}

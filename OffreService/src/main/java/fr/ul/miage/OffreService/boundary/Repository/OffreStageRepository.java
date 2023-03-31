package fr.ul.miage.OffreService.boundary.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.ul.miage.OffreService.entity.OffreStage;

public interface OffreStageRepository extends JpaRepository<OffreStage, UUID> {

    Iterable<? extends OffreStage> findByDomaine(String domaine);
    Iterable<? extends OffreStage> findByNiveauEtudesStage(String niveau);
    Iterable<? extends OffreStage> findByExperienceRequiseStage(String experience);
    Iterable<? extends OffreStage> findByPays(String pays);
    Iterable<? extends OffreStage> findByVille(String ville);
    Iterable<? extends OffreStage> findByNom(String nom);
    Iterable<? extends OffreStage> findByDatePublication(String date);
    Iterable<? extends OffreStage> findByDateDebutStage(String dateDebut);
    Iterable<? extends OffreStage> findByDureeStage(String duree);
    Iterable<? extends OffreStage> findBySalaireStage(String salaire);
    Iterable<? extends OffreStage> findByIndemnisation(String indemnisation);
    Iterable<OffreStage> findByOrganisationId(UUID organisationId);
    Optional<OffreStage> findById(UUID uuid);
}

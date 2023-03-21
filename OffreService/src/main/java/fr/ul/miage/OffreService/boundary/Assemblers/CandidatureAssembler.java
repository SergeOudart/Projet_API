package fr.ul.miage.OffreService.boundary.Assemblers;

import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import fr.ul.miage.OffreService.controllers.OffreStageController;
import fr.ul.miage.OffreService.entity.Candidature;

import java.util.stream.StreamSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CandidatureAssembler implements RepresentationModelAssembler<Candidature, EntityModel<Candidature>> {

    @Override
    public EntityModel<Candidature> toModel(Candidature candidature) {
        return EntityModel.of(candidature,
                linkTo(methodOn(OffreStageController.class).getOffreById(candidature.getId())).withSelfRel(),
                linkTo(methodOn(OffreStageController.class).getAllOffres()).withRel("collection"));
    }

    @Override
    public CollectionModel<EntityModel<Candidature>> toCollectionModel(Iterable<? extends Candidature> entities) {
        List<EntityModel<Candidature>> classModel = StreamSupport
            .stream(entities.spliterator(), false)
            .map(this::toModel)
            .toList();

        return CollectionModel.of(classModel, linkTo(methodOn(OffreStageController.class)
            .getAllOffres()).withSelfRel());
    }
}

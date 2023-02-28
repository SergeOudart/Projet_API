package fr.ul.miage.OffreService.boundary;

import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import fr.ul.miage.OffreService.controllers.OffreStageController;
import fr.ul.miage.OffreService.entity.OffreStage;

import java.util.stream.StreamSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class OffreStageAssembler implements RepresentationModelAssembler<OffreStage, EntityModel<OffreStage>> {

    @Override
    public EntityModel<OffreStage> toModel(OffreStage offreStage) {
        return EntityModel.of(offreStage,
                linkTo(methodOn(OffreStageController.class).getOffreById(offreStage.getId())).withSelfRel(),
                linkTo(methodOn(OffreStageController.class).getAllOffres()).withRel("collection"));
    }

    @Override
    public CollectionModel<EntityModel<OffreStage>> toCollectionModel(Iterable<? extends OffreStage> entities) {
        List<EntityModel<OffreStage>> intervenanModel = StreamSupport
            .stream(entities.spliterator(), false)
            .map(this::toModel)
            .toList();

        return CollectionModel.of(intervenanModel, linkTo(methodOn(OffreStageController.class)
            .getAllOffres()).withSelfRel());
    }
}
package fr.ul.miage.OffreService.boundary;

import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import fr.ul.miage.OffreService.controllers.OffreStageController;
import fr.ul.miage.OffreService.entity.ProcessusRecrutement;

import java.util.stream.StreamSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class ProcessusRecrutementAssembler implements RepresentationModelAssembler<ProcessusRecrutement, EntityModel<ProcessusRecrutement>> {

    @Override
    public EntityModel<ProcessusRecrutement> toModel(ProcessusRecrutement processusRecrutement) {
        return EntityModel.of(processusRecrutement,
                linkTo(methodOn(OffreStageController.class).getOffreById(processusRecrutement.getId())).withSelfRel(),
                linkTo(methodOn(OffreStageController.class).getAllOffres()).withRel("collection"));
    }

    @Override
    public CollectionModel<EntityModel<ProcessusRecrutement>> toCollectionModel(Iterable<? extends ProcessusRecrutement> entities) {
        List<EntityModel<ProcessusRecrutement>> classModel = StreamSupport
            .stream(entities.spliterator(), false)
            .map(this::toModel)
            .toList();

        return CollectionModel.of(classModel, linkTo(methodOn(OffreStageController.class)
            .getAllOffres()).withSelfRel());
    }
}

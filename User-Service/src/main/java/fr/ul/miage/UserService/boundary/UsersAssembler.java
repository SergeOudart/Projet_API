package fr.ul.miage.UserService.boundary;

import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import fr.ul.miage.UserService.controllers.UsersController;
import fr.ul.miage.UserService.entity.Users;

import java.util.stream.StreamSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class UsersAssembler implements RepresentationModelAssembler<Users, EntityModel<Users>> {

    @Override
    public EntityModel<Users> toModel(Users user) {
        return EntityModel.of(user,
                linkTo(methodOn(UsersController.class).getUserById(user.getId())).withSelfRel(),
                linkTo(methodOn(UsersController.class).getAllUsers()).withRel("collection"));
    }

    @Override
    public CollectionModel<EntityModel<Users>> toCollectionModel(Iterable<? extends Users> entities) {
        List<EntityModel<Users>> classModel = StreamSupport
            .stream(entities.spliterator(), false)
            .map(this::toModel)
            .toList();

        return CollectionModel.of(classModel, linkTo(methodOn(UsersController.class)
            .getAllUsers()).withSelfRel());
    }
}

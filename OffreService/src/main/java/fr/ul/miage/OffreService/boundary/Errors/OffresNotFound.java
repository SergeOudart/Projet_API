package fr.ul.miage.OffreService.boundary.Errors;


class OffresNotFound extends RuntimeException {

    OffresNotFound() {
        super("Offres not found");
    }
}
package fr.ul.miage.UserService.boundary;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.ul.miage.UserService.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{
    Optional<Users> findByUsername(String username);
    Optional<Users> findById(UUID uuid);
    
}

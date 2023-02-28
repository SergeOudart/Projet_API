package fr.ul.miage.UserService.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.ul.miage.UserService.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{
    
}

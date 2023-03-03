package fr.ul.miage.UserService.boundary.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.ul.miage.UserService.entity.Users;
import fr.ul.miage.UserService.boundary.UsersRepository;

@Configuration
public class DatabaseLoader {
    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Bean
    CommandLineRunner initDatabase(UsersRepository repository) {

        return args -> {
            /*log.info(
                "Preloading info in database" + repository.save(new Users(
                    "soudart",
                    "2 rue du Test",
                    "sergeoudart@test.com",
                    "Oudart",
                    "Serge"
                ))
            );
            log.info(
                "Preloading info in database" + repository.save(new Users(
                    "rguillaume",
                    "4 rue du Test",
                    "guillaumereinert@test.fr",
                    "Reinert",
                    "Guillaume"
                ))
            );*/
        };
    }
}

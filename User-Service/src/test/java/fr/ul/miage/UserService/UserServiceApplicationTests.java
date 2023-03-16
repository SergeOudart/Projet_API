package fr.ul.miage.UserService;

import static io.restassured.RestAssured.when;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.CoreMatchers.*;

import fr.ul.miage.UserService.boundary.UsersRepository;
import fr.ul.miage.UserService.entity.Users;
import io.restassured.RestAssured;
import fr.ul.miage.UserService.controllers.UsersController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
	"spring.datasource.url=jdbc:h2:mem:m2db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
	"spring.datasource.driver-class-name=org.h2.Driver",
	"spring.datasource.username=sa",
	"spring.datasource.password=password",
	"spring.jpa.hibernate.ddl-auto=create-drop",
	"spring.jpa.defer-datasource-initialization=true"})
class UserServiceApplicationTests {

	@LocalServerPort
	int port;

	@Autowired
	UsersRepository ur;
	UsersController userController;

	@BeforeEach
	public void setupContext() {
		ur.deleteAll();
		RestAssured.port = port;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void ping() {
		when().get("/Users").then().statusCode(HttpStatus.SC_OK);
	}

	@Test
	void getAllUsers() {
		Users user = new Users("soudart", "1 rue de oui", "serge@oudart.com", "Oudart", "Serge");
		ur.save(user);
		when().get("/Users").then().statusCode(HttpStatus.SC_OK)
            .and().assertThat().body("size()",equalTo(1));
	}
}

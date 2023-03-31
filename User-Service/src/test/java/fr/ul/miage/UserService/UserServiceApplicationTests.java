package fr.ul.miage.UserService;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.CoreMatchers.*;

import java.util.UUID;

import fr.ul.miage.UserService.boundary.UsersRepository;
import fr.ul.miage.UserService.entity.Users;
import io.restassured.RestAssured;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
	"spring.datasource.url=jdbc:h2:mem:m2db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
	"spring.datasource.driver-class-name=org.h2.Driver",
	"spring.datasource.username=sa",
	"spring.datasource.password=password",
	"spring.jpa.hibernate.ddl-auto=create-drop",	
	"spring.jpa.defer-datasource-initialization=true",
	"spring.jpa.database-plateform=org.hibernate.dialect.H2Dialectx",
	"spring.h2.console.enabled=true",
	"spring.jpa.hibernate.ddl-auto=update"})

class UserServiceApplicationTests {

	@LocalServerPort
	int port;

	@Autowired
    private UsersRepository ur;

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
		UUID uuid = UUID.fromString("3c0969ac-c6e3-40f2-9fc8-2a59b8987918");
		UUID uuid2 = UUID.fromString("3c0969ac-c6e3-40f2-9fc8-2a59b8987919");
		UUID uuid3 = UUID.fromString("3c0969ac-c6e3-40f2-9fc8-2a59b8987920");
		Users user = new Users(uuid, "soudart", "1 rue de oui", "serge@oudart.com", "Oudart", "Serge");
		Users user2 = new Users(uuid2, "soudart2", "1 rue de oui", "serge2@oudart.com", "Oudart2", "Serge2");
		Users user3 = new Users(uuid3, "soudart3", "1 rue de oui", "serge3@oudart.com", "Oudart3", "Serge3");
		ur.save(user);
		ur.save(user2);
		ur.save(user3);
		when().get("/Users").then().statusCode(HttpStatus.SC_OK)
			.and().assertThat().body("_embedded.usersList.size()",equalTo(3));
	 }

	@Test
	 void getOneUser(){
		String uuidString = "3c0969ac-c6e3-40f2-9fc8-2a59b8987918";
		UUID uuid = UUID.fromString(uuidString);
		Users user = new Users(uuid, "soudart", "1 rue de oui", "serge@oudart.com", "Oudart", "Serge");
		ur.save(user);
		when().get("/Users/3c0969ac-c6e3-40f2-9fc8-2a59b8987918").then().statusCode(HttpStatus.SC_OK)
		.and().assertThat().body("id",equalTo(uuidString))
		.and().assertThat().body("username",equalTo("soudart"))
		.and().assertThat().body("adresse",equalTo("1 rue de oui"))
		.and().assertThat().body("email",equalTo("serge@oudart.com"))
		.and().assertThat().body("nom",equalTo("Oudart"))
		.and().assertThat().body("prenom",equalTo("Serge"));
	 }

	@Test
	 void getOneUserNotFound(){
		String uuidString = "3c0969ac-c6e3-40f2-9fc8-2a59b8987918";
		UUID uuid = UUID.fromString(uuidString);
		Users user = new Users(uuid, "soudart", "1 rue de oui", "serge@oudart.com", "Oudart", "Serge");
		ur.save(user);
		when().get("/Users/3c0969ac-c6e3-40f2-9fc8-2a59b8987919").then().statusCode(HttpStatus.SC_NOT_FOUND);
	 }

	@Test
	 void createUser() throws JSONException{
		String uuidString = "3c0969ac-c6e3-40f2-9fc8-2a59b8987918";

		JSONObject json = new JSONObject()
		.put("id", uuidString)
		.put("username", "soudart")
		.put("adresse", "1 rue de oui")
		.put("email", "serge@oudart.com")
		.put("nom", "Oudart")
		.put("prenom", "Serge");

		given()
			.contentType("application/json")
			.body(json.toString())
		.when()
			.post("/Users")
		.then()
			.statusCode(HttpStatus.SC_CREATED);
	 }

	@Test
	void getUserByLogin() {
		String uuidString = "3c0969ac-c6e3-40f2-9fc8-2a59b8987918";
		UUID uuid = UUID.fromString(uuidString);
		Users user = new Users(uuid, "soudart", "1 rue de oui", "serge@oudart.fr", "Oudart", "Serge");
		ur.save(user);
		when().get("/Users/login/soudart").then().statusCode(HttpStatus.SC_OK)
		.and().assertThat().body("id",equalTo(uuidString));
	}

	@Test
	void getUserByLoginNotFound() {
		String uuidString = "3c0969ac-c6e3-40f2-9fc8-2a59b8987918";
		UUID uuid = UUID.fromString(uuidString);
		Users user = new Users(uuid, "soudart", "1 rue de oui", "serge@oudart.fr", "Oudart", "Serge");
		ur.save(user);
		when().get("/Users/login/soudart2").then().statusCode(HttpStatus.SC_NOT_FOUND);
	}
}

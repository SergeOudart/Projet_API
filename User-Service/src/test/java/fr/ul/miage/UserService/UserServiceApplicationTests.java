package fr.ul.miage.UserService;

import static io.restassured.RestAssured.when;

import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
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
import fr.ul.miage.UserService.controllers.UsersController;



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

/* 	@Test
	public void getAllUsersAPI() throws Exception 
{
	Users user = new Users("soudart", "1 rue de oui", "serge@oudart.com", "Oudart", "Serge");
	ur.save(user);
  	mvc.perform(MockMvcRequestBuilders
  			.get("/Users")
  			.accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.Users").exists())
      .andExpect(MockMvcResultMatchers.jsonPath("$.Users[*].id").isNotEmpty());
}*/


 /* 	@Test 
	public void testReturnAll(){

		Users user = new Users("test1", "1 rue de oui", "serge@oudart.com", "Oudart", "Serge");
		Users user3 = new Users("test2", "1 rue de oui", "usertest2@oudart.com", "nom2", "prenom2");
		List<Users> userList = new ArrayList<>();
		userList.add(user);
		userList.add(user3);
		ur.save(user);
		ur.save(user3);

		Mockito.when(ur.findAll()).thenReturn(userList);
		//Mockito.when(userAssembler.toCollectionModel(userList)).thenReturn(new CollectionModel<>(userList));

		ResponseEntity<?> response = userController.getAllUsers();
      	assertEquals(HttpStatus.SC_OK, response.getStatusCode());
      	assertNotNull(response.getBody());


	}*/

	/*@Test
	public void testfindAll() throws Exception {
	  Users user = new Users("test1", "1 rue de oui", "serge@oudart.com", "Oudart", "Serge");
	  List<Users> userList = Arrays.asList(user);
   
	  Mockito.when(ur.findAll()).thenReturn(userList);
   
	  mockMvc.perform(get("/Users"))
		  .andExpect(status().isOk())
		  .andExpect(jsonPath("$", Matchers.hasSize(1)))
		  .andExpect(jsonPath("$[0].username", Matchers.is("test1")))
		  .andReturn();
	}*/
}

package fr.ul.miage.OffreService.OffreService;

import java.sql.Date;
import java.util.UUID;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import fr.ul.miage.OffreService.boundary.Repository.OffreStageRepository;
import fr.ul.miage.OffreService.entity.OffreStage;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
class OffreServiceApplicationTests {

	@LocalServerPort
	int port;

	@Autowired
	private OffreStageRepository offreRepository;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void setupContext() {
		offreRepository.deleteAll();
		RestAssured.port = port;
		String uuidString = "3c0969ac-c6e3-40f2-9fc8-2a59b8987918";
		String uuidString2 = "3c0969ac-c6e3-40f2-9fc8-2a59b8987919";
		UUID uuid = UUID.fromString(uuidString);
		UUID uuid2 = UUID.fromString(uuidString2);
		String str="2023-02-01";  
		String str2="2023-04-01";  
		Date d1 = Date.valueOf(str);
		Date d2 = Date.valueOf(str2);
        OffreStage offre = new OffreStage(
			uuid
			,"test"
			,"domaine"
			,"test decription"
			,d1
			,"1"
			,"2"
			,d2
			,"2"
			,3
			,"2"
			,uuid2
			,"France"
			,"Metz"
			,57000
			,"1 rue"
			,2.4
			,2.3
			,"95555"
			,"aaaa"
		);
		OffreStage offre1 = new OffreStage(
			UUID.randomUUID()
			,"test"
			,"domaine"
			,"test decription"
			,Date.valueOf("2023-02-01")
			,"1"
			,"2"
			,Date.valueOf("2023-04-01")
			,"2"
			,3
			,"2"
			,UUID.randomUUID()
			,"France"
			,"Nancy"
			,57000
			,"1 rue"
			,2.4
			,2.3
			,"95555"
			,"aaaa"
		);
		offreRepository.save(offre1);
		offreRepository.save(offre);
	}

	@Test
	 void getOneOffre(){		
		float longitudeTest = 2.4f;
		float latitudeTest = 2.3f;
		when().get("/Offres/3c0969ac-c6e3-40f2-9fc8-2a59b8987918").then()
		.and()
		.assertThat()
		.body("nom",equalTo("test"))
		.body("domaine",equalTo("domaine"))
		.body("descriptionStage",equalTo("test decription"))
		.body("datePublication",equalTo("2023-02-01"))
		.body("niveauEtudesStage",equalTo("1"))
		.body("experienceRequiseStage",equalTo("2"))
		.body("dateDebutStage",equalTo("2023-04-01"))
		.body("dureeStage",equalTo("2"))
		.body("salaireStage",equalTo(3))
		.body("indemnisation",equalTo("2"))
		.body("organisationId",equalTo("3c0969ac-c6e3-40f2-9fc8-2a59b8987919"))
		.body("pays",equalTo("France"))
		.body("ville",equalTo("Metz"))
		.body("codePostal",equalTo(57000))
		.body("rue",equalTo("1 rue"))
		.body("longitude",equalTo(longitudeTest))
		.body("latitude",equalTo(latitudeTest))
		.body("telephone",equalTo("95555"))
		.body("url",equalTo("aaaa"));
	 }

	 @Test
	 void getAllOffres(){
		when().get("/Offres").then().
		assertThat().
		body("size()", is(2));
	 }

	 @Test
	 void getOffresByDomaine(){
		when().get("/filtre/domaine=domaine").then().
		assertThat().
		body("size()", is(2));
	 }

	@Test
	 void getOffresByVille(){
		when().get("/filtre/ville=Metz").then()
		.assertThat().
		body("_embedded.offreStages.size()", is(1));
	}

	 @Test
	 void offreNotFound() {
		when().get("/Offres/3c0969ac-c6e3-40f2-9fc8-2a59b8987917").then()
		.statusCode(HttpStatus.SC_NOT_FOUND);
	 }

	 @Test
	 void offreNotFoundByDomaine() {
		when().get("/filtre/domaine=mauvais").then()
		.statusCode(HttpStatus.SC_NOT_FOUND);
	 }

	 @Test
	 void offreNotFoundByVille() {
		when().get("/filtre/ville=mauvais").then().
		statusCode(HttpStatus.SC_NOT_FOUND);
	 }

	 @Test
	 void createOffre() throws JSONException {
		String uuidString = "3c0969ac-c6e3-40f2-9fc8-2a59b8987918";
		UUID uuid = UUID.fromString(uuidString);
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("nom", "test")
		.put("domaine", "domaine")
		.put("descriptionStage", "test decription")
		.put("datePublication", "2023-02-01")
		.put("niveauEtudesStage", "1")
		.put("experienceRequiseStage", "2")
		.put("dateDebutStage", "2023-04-01")
		.put("dureeStage", "2")
		.put("salaireStage", 3)
		.put("indemnisation", "2")
		.put("organisationId", uuid)
		.put("pays", "France")
		.put("ville", "Metz")
		.put("codePostal", 57000)
		.put("rue", "1 rue")
		.put("longitude", 2.4)
		.put("latitude", 2.3)
		.put("telephone", "95555")
		.put("url", "aaaa");

		given()
		.contentType("application/json")
		.body(requestParams.toString())
		.when()
		.post("/Offres")
		.then()
		.statusCode(HttpStatus.SC_CREATED);
	 }

	 
	@Test
	void deleteOffre() {
		given()
		.when()
		.delete("/Offres/3c0969ac-c6e3-40f2-9fc8-2a59b8987918")
		.then()
		.statusCode(HttpStatus.SC_NO_CONTENT);
	}

	@Test
	void deleteOffreNotFound() {
		given()
		.when()
		.delete("/Offres/3c0969ac-c6e3-40f2-9fc8-2a59b8987917")
		.then()
		.statusCode(HttpStatus.SC_NOT_FOUND);
	}

	@Test
	void updateOffre() throws JSONException {
		String uuidString = "3c0969ac-c6e3-40f2-9fc8-2a59b8987918";
		UUID uuid = UUID.fromString(uuidString);
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("nom", "test")
		.put("domaine", "domaineModif")
		.put("descriptionStage", "test decription")
		.put("datePublication", "2023-02-01")
		.put("niveauEtudesStage", "1")
		.put("experienceRequiseStage", "2")
		.put("dateDebutStage", "2023-04-01")
		.put("dureeStage", "2")
		.put("salaireStage", 3)
		.put("indemnisation", "2")
		.put("organisationId", uuid)
		.put("pays", "France")
		.put("ville", "Metz")
		.put("codePostal", 57000)
		.put("rue", "1 rue")
		.put("longitude", 2.4)
		.put("latitude", 2.3)
		.put("telephone", "95555")
		.put("url", "aaaa");

		given()
		.contentType("application/json")
		.body(requestParams.toString())
		.when()
		.put("/Offres/3c0969ac-c6e3-40f2-9fc8-2a59b8987918")
		.then()
		.statusCode(HttpStatus.SC_OK);
	}

	@Test
	void updateNotExisting() {
		given()
		.contentType("application/json")
		.when()
		.put("/Offres/3c0969ac-c6e3-40f2-9fc8-2a59b8987917")
		.then()
		.statusCode(HttpStatus.SC_BAD_REQUEST);
	}

	@Test
	void postCandidature() throws JSONException {
		String uuidString = "3c0969ac-c6e3-40f2-9fc8-2a59b8987918";
		UUID uuid = UUID.fromString(uuidString);
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("idOffreStage", uuid)
		.put("idUser", uuid)
		.put("dateCandidature", "2023-04-01")
		.put("etatCandidature", "en attente");

	}




}

package fr.ul.miage.OffreService.OffreService;

import java.sql.Date;
import java.util.UUID;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import fr.ul.miage.OffreService.boundary.OffreStageAssembler;
import fr.ul.miage.OffreService.boundary.OffreStageRepository;
import fr.ul.miage.OffreService.controllers.OffreStageController;
import fr.ul.miage.OffreService.entity.OffreStage;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.*;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;







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

	@Autowired
	private OffreStageRepository offreRepository;

	@Autowired
	private OffreStageAssembler offreAssembler;

	@Autowired
    private OffreStageController offreController;


	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void setupContext() {
		offreRepository.deleteAll();
		//RestAssured.port = port;
	}


	@Test
	 void getOneOffre(){
	
		String test = "3c0969ac-c6e3-40f2-9fc8-2a59b8987918";
		UUID uuid = UUID.fromString(test);
		String a = uuid.toString();
		UUID uuid2 = UUID.randomUUID();
		String str="2023-02-01";  
		String str2="2023-04-01";  
		Date d1 = Date.valueOf(str);
		Date d2 = Date.valueOf(str2);
        OffreStage offre = new OffreStage("test"
		, "domaine"
		,"test decription"
		,d1
		,"1"
		,"2"
		,d2
		,"2"
		,3
		,"2"
		,uuid2,"France"
		,"Metz"
		,57000
		,"1 rue"
		,2.4
		,2.3
		,"95555"
		,"aaaa");
        offre.setId(uuid);
		offreRepository.save(offre);
		when().get("/offres/3c0969ac-c6e3-40f2-9fc8-2a59b8987918").then().statusCode(HttpStatus.SC_OK)
		.and()
		.assertThat()
		.body("id",equalTo(uuid));
        
		
		/*Mockito.when(offreRepository.findById(uuid)).thenReturn(Optional.of(offre));
        Mockito.when(offreAssembler.toModel(offre)).thenReturn(new OffreModel());

        ResponseEntity<?> response = offreController.getOffreById(uuid);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(response.getBody() instanceof OffreModel);*/

	 }

}

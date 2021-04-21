package com.algaworsk.algafood.cozinha;

import static io.restassured.RestAssured.given;

import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.AlgafoodApiApplication;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AlgafoodApiApplication.class)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaAPI {

	@LocalServerPort
	private int port;

	@Autowired
	//private DatabaseCleaner dataBaseCleaner;
	private Flyway flyway;
	
	
	@Before
	public void setUp() {
		// aqui mostra certinho o request e response
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
	//	dataBaseCleaner.clearTables();

		flyway.migrate();
	}

	@Test
	public void deveConter1Cozinhas_QuandoConsultarCozinhas() {
		given().accept(ContentType.JSON).when().get().then().body("", Matchers.hasSize(1));
	}

	@Test
	public void deveRetornar200_QuandoConsultarCozinhas() {

		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		given().basePath("/cozinhas").port(port).accept(ContentType.JSON).when().get().then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void testRetornarStatus201_QuandoCadastrarCozinha() {

		given().body("{ \"nome\": \"Chinesa\" }").contentType(ContentType.JSON).accept(ContentType.JSON).when().post()
				.then().statusCode(HttpStatus.CREATED.value());

	}
	
	private void prepararDados() {
		
	}

}
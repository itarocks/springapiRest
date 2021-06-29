package com.algaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

	private static final int COZINHA_ID_INEXISTENTE = 100;

	private static final String CAMINHO_JSON = "/Users/itamarrocha/eclipse-workspace/especialista/Spring/algafood-api/src/test/resources/Json/";

	List<Cozinha> cozinhas = new ArrayList();
	
	Cozinha cozinhaAmericana;

	public Integer totalCozinhas;

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner dataBaseCleaner;
	// private Flyway flyway;

	@Autowired
	CozinhaRepository cozinhaRepository;

	@Before
	public void setUp() {
		// aqui mostra certinho o request e response
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		dataBaseCleaner.clearTables();
		prepararDados();

		// flyway.migrate();
	}

	@Test
	public void deveConter1Cozinhas_QuandoConsultarCozinhas() {
		given().accept(ContentType.JSON).when().get().then().body("", Matchers.hasSize(totalCozinhas.intValue()));
	}

	@Test
	public void deveRetornar200_QuandoConsultarCozinhas() {

		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		given().basePath("/cozinhas").port(port).accept(ContentType.JSON).when().get().then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() throws IOException {

		//String jsonBody = generateStringFromResource(
		//		"/Users/itamarrocha/eclipse-workspace/especialista/Spring/algafood-api/src/test/java/resources/Json/criaCozinha.json");

		 String jsonBody = new String(Files.readAllBytes(Paths.get(CAMINHO_JSON.concat("criaCozinha.json"))));  
		
		given().body(jsonBody).contentType(ContentType.JSON).accept(ContentType.JSON).when().post().then()
				.statusCode(HttpStatus.CREATED.value());

	}

	@Test
	public void deveRetornarRespostaStatusCorretoCozinhaExistente() {

		given().pathParam("cozinhaId", cozinhaAmericana.getId()).accept(ContentType.JSON).when().get("/{cozinhaId}").then()
				.statusCode(HttpStatus.OK.value()).body("nome", equalTo(cozinhaAmericana.getNome()));
	}

	@Test
	public void deveRetornarRespostaStatus404CozinhaInexistente() {

		given().pathParam("cozinhaId", COZINHA_ID_INEXISTENTE).accept(ContentType.JSON).when().get("/{cozinhaId}").then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepararDados() {

		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);

		cozinhas.add(cozinha1);

		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Americana");
		cozinhaRepository.save(cozinha2);
		
		cozinhaAmericana = cozinha2;

		cozinhas.add(cozinha2);

		totalCozinhas = cozinhas.size();
		System.out.println("Total de cozinhas : " + cozinhas.size());

	}

	public String generateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}

}
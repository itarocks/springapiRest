package com.algaworsk.algafood.cozinha;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.AlgafoodApiApplication;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AlgafoodApiApplication.class)
public class CadastroCozinhaAPI {

	@LocalServerPort
	private int port;

	@Before
	public void setUp() {
		// aqui mostra certinho o request e response
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";

	}

	@Test
	public void deveConter2Cozinhas_QuandoConsultarCozinhas() {
		given().accept(ContentType.JSON).when().get().then().body("", Matchers.hasSize(1));
	}

	@Test
	public void deveRetornar200_QuandoConsultarCozinhas() {

		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		given().basePath("/cozinhas").port(port).accept(ContentType.JSON).when().get().then()
				.statusCode(HttpStatus.OK.value());
	}

}
package com.algaworsk.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlgafoodApiApplication.class)

public class CadastroCozinhaIntegrationTest {
	
	
	@Autowired
	CadastroCozinhaService cadastroCozinha;
	
	
	@Test
	public void testarCadastroCozinhaComSucesso() {
		
		Cozinha novaCozinha = new Cozinha();
		
		novaCozinha.setNome("Asiatica");
		
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
		
		assertThat(novaCozinha).isNotNull();
		
		assertThat(novaCozinha.getId()).isNotNull();
		
	}
	
	
	@Test(expected = EntidadeEmUsoException.class)
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		
		Cozinha novaCozinha = new Cozinha();
		
		novaCozinha.setId(1l);
		
		cadastroCozinha.excluir(novaCozinha.getId());
		
		Cozinha resultado = new Cozinha();
		
		cadastroCozinha.buscarOuFalhar(1l);
		
		assertThat(resultado).isNotNull();
	
		
	}

}

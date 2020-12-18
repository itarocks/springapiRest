package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.algaworks.algafood.di.modelo.Cliente;

// Uma das formas de dizer que a classe é um component spring
//@Profile("prod")
@Primary
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorEmail implements Notificador {
	
	@Autowired
	private NotificadorProperties properties;
	
	/*private boolean caixaAlta;
	private String hostServidorSmtp;

	public NotificadorEmail(String hostServidorSmtp) {
		this.hostServidorSmtp = hostServidorSmtp;
		System.out.println("NotificadorEmail");
	}
*/
	//pega os valores do arquivo properties
	//@Value("${notificador.email.host-servidor}")
	//private String host;

	public NotificadorEmail() {
		System.out.println("Notificador email Real");
		// TODO Auto-generated constructor stub
	}

	public void notificar(Cliente cliente, String mensagem) {	
		System.out.println("host: " + properties.getHostservidor());
		System.out.printf("Notificando %s através do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), mensagem);
	}

	/*public boolean isCaixaAlta() {
		return caixaAlta;
	}

	public void setCaixaAlta(boolean caixaAlta) {
		this.caixaAlta = caixaAlta;
	}

	public String getHostServidorSmtp() {
		return hostServidorSmtp;
	}

	public void setHostServidorSmtp(String hostServidorSmtp) {
		this.hostServidorSmtp = hostServidorSmtp;
	}
*/
}

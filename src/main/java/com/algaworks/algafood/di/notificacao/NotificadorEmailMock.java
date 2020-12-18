package com.algaworks.algafood.di.notificacao;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.algaworks.algafood.di.modelo.Cliente;

// Uma das formas de dizer que a classe é um component spring
//@Profile("dev")
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmailMock implements Notificador {
	
	/*private boolean caixaAlta;
	private String hostServidorSmtp;

	public NotificadorEmail(String hostServidorSmtp) {
		this.hostServidorSmtp = hostServidorSmtp;
		System.out.println("NotificadorEmail");
	}
*/

	
	
	public void notificar(Cliente cliente, String mensagem) {		
		System.out.printf("Mock: Notificaçao seria enviada para %s: %s\n", cliente.getNome(), cliente.getEmail(), mensagem);
	}

	public NotificadorEmailMock() {
		System.out.println("Notificador email Mock");
		// TODO Auto-generated constructor stub
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

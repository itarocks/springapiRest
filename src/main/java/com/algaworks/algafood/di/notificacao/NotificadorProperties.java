package com.algaworks.algafood.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {
	
	private String hostServidor;

	public String getHostservidor() {
		return hostServidor;
	}

	public void setHostservidor(String hostservidor) {
		this.hostServidor = hostservidor;
	}
	
	

}

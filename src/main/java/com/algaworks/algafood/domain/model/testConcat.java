package com.algaworks.algafood.domain.model;

public class testConcat {
	
	
	
	static final String API = "/api/usuarios";


	public static void main(String...args) {
		
		String teste = API.concat("/autenticar"); 
		
	    System.out.println("valor do concat" + teste);
	    
	    
		
		
	}
}

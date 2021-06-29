package com.algaworks.algafood;

import java.util.ArrayList;
import java.util.List;

public class TesteArray {
	
	
	public static void main(String...args) {
		
		
		List<Integer> list = new ArrayList<Integer>();
		
		Integer x = 5;
		list.add(x);
		x = 10;
		list.add(x);
		
		for(Integer item : list) {
			
			
			if(item == 10)
			System.out.println("o valor do item "+ item );
		}
		

		
	}

}

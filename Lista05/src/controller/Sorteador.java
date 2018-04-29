package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Sorteador {
	
	public List<Integer> sorteador() {
		Random gerador = new Random();
		List<Integer> numeros = new ArrayList<Integer>();
		Integer temp = 0;
		
		for(int i = 0; i < 6; i++) {
			temp = gerador.nextInt(60) + 1;
			if(!numeros.contains(temp)) numeros.add(temp);
			else i--;
		}
		Collections.sort(numeros);
		return numeros;
	}

}

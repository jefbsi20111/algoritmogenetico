package br.edu.ufam.icomp.nsga.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class Util {
	
	public static final int LIMITE_INFERIOR_VETOR = -1;
	
	public static InputStreamReader getConteudoArquivo(String path){
		InputStream is;
		InputStreamReader isr = null;
		try {
			is = new FileInputStream(path);
			isr = new InputStreamReader(is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isr;
	}
	
	public static int sorteio(int limiteInferior, int limiteSuperior){
		Random gerador = new Random();
		int valor = gerador.nextInt(limiteSuperior);
		while (valor <= limiteInferior){
			valor = gerador.nextInt(limiteSuperior);
		}
		return valor;
	}
	
	  /** 
	  * Returns a permutation vector between the 0 and (length - 1) 
	  */
	public static int [] intPermutation(int length){
	    int [] aux    = new int[length];
	    int [] result = new int[length];
	        
	    // First, create an array from 0 to length - 1. 
	    // Also is needed to create an random array of size length
	    for (int i = 0; i < length; i++) {
	      result[i] = i;
	      aux[i] = PseudoRandom.randInt(0,length-1);
	    } // for
	        
	    // Sort the random array with effect in result, and then we obtain a
	    // permutation array between 0 and length - 1
	    for (int i = 0; i < length; i++) {
	      for (int j = i + 1; j < length; j++) {
	        if (aux[i] > aux[j]) {
	          int tmp;
	          tmp = aux[i];
	          aux[i] = aux[j];
	          aux[j] = tmp;
	          tmp = result[i];
	          result[i] = result[j];
	          result[j] = tmp;
	        } // if
	      } // for
	    } // for
	        
	    return result;
	  }// intPermutation
}

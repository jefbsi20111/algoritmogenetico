package br.edu.ufam.icomp.nsga.util;

import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Test;

public class UtilTest {
	private int tamanhoPopulacao = 50;

	@Test
	public void testIntPermutation() {
		int [] vetor = Util.intPermutation(tamanhoPopulacao);
		int repetidos = 0;
		for (int i=0; i<tamanhoPopulacao-1; i++){
			for (int j=i+1; j<tamanhoPopulacao; j++){
				if(vetor[i] == vetor[j]){
					repetidos++;
				}
			}
		}
		/*for(int i=0; i<tamanhoPopulacao; i++){
			System.out.println(vetor[i]);
		}*/
		Assert.assertEquals(0, repetidos);
	}
	
	@Test
	public void deveCarregarTextoDoArquivo(){
		InputStreamReader isr = Util.getConteudoArquivo("amostra10.dat");
		Assert.assertNotNull(isr);
	}

}

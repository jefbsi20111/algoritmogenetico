package br.edu.ufam.icomp.nsga.bean;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PopulacaoTest {
	
	private Populacao populacao;
	
	@Before
	public void init(){
	}

	@Test
	public void deveGerar50individuosAleatorios() {
		populacao = new Populacao();
		populacao.setListaDeIndividuos((Populacao.criarPopulacao(50, 6, 3, 3)));
		int i=0;
		for (Individuo solucao : populacao.getListaDeIndividuos()) {
			i++;
			//System.out.println(solucao);
		}
		Assert.assertEquals(50, i);
	}

	@Test
	public void deveSelecionarOs5Melhores(){
		List<Individuo> lista = Individuo.convertFromFile("amostra10.dat");
		List<Individuo> listaMelhores = new ArrayList<Individuo>(); 
		populacao = new Populacao();
		populacao.setListaDeIndividuos(lista);
		
		for(int i=0; i<5; i++){
			Individuo individuo = populacao.selecao();
			listaMelhores.add(individuo);
			System.out.println(individuo);
		}
		Assert.assertEquals(5, listaMelhores.size());
	}
}

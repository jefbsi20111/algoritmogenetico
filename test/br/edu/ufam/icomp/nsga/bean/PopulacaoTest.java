package br.edu.ufam.icomp.nsga.bean;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.ufam.icomp.nsga.enums.TipoCromossomo;

public class PopulacaoTest {

	private Populacao populacao;

	@Before
	public void init() {
	}

	@Test
	public void deveGerar50individuosAleatorios() {
		populacao = new Populacao();
		populacao.setListaDeIndividuos((Populacao.criarPopulacao(50, 6, 3, 3)));
		int i = 0;
		for (Individuo solucao : populacao.getListaDeIndividuos()) {
			i++;
			// System.out.println(solucao);
		}
		Assert.assertEquals(50, i);
	}

	@Test
	public void deveSelecionarOs5Melhores() {
		List<Individuo> lista = Individuo.convertFromFile("amostra10.dat");
		List<Individuo> listaMelhores = new ArrayList<Individuo>();
		populacao = new Populacao();
		populacao.setListaDeIndividuos(lista);

		for (int i = 0; i < 5; i++) {
			Individuo individuo = populacao.selecao();
			listaMelhores.add(individuo);
			// System.out.println(individuo);
		}
		Assert.assertEquals(5, listaMelhores.size());
	}

	@Test
	public void deveTrocarTecnicas() {
		List<Individuo> lista = Individuo.convertFromFile("amostra10.dat");
		Populacao populacao = new Populacao();
		populacao.setListaDeIndividuos(lista);

		Individuo individuo1 = populacao.selecao();
		Individuo individuo2 = populacao.selecao();

		lista = populacao.cruzamento(individuo1, individuo2);
		
		Assert.assertEquals(2, lista.size());

		Assert.assertNotEquals(individuo1, lista.get(0));
		Assert.assertNotEquals(individuo1, lista.get(1));
		Assert.assertNotEquals(individuo2, lista.get(0));
		Assert.assertNotEquals(individuo2, lista.get(1));
		
		/*
		 * System.out.println(individuo1); System.out.println(individuo2);
		 * System.out.println(lista.get(0)); System.out.println(lista.get(1));
		 */
	}
	

	@Test
	public void deveUnirPopulacoes() {
		List<Individuo> lista = Individuo.convertFromFile("amostra10.dat");
		Populacao populacao = new Populacao();
		populacao.setListaDeIndividuos(lista);

		Individuo individuo1 = populacao.selecao();
		Individuo individuo2 = populacao.selecao();

		lista = populacao.cruzamento(individuo1, individuo2);
		
		Populacao uniao = new Populacao();
		
		uniao.addAll(populacao.getListaDeIndividuos());
		Assert.assertEquals(10, uniao.size());
		
		uniao.addAll(lista);
		Assert.assertEquals(12, uniao.size());
	}
	
	@Test
	public void deveSalvarPopulacaoOrdenadaEmArquivo() {
		List<Individuo> lista = Individuo.convertFromFile("amostra50.dat");
		Populacao populacao = new Populacao();
		populacao.setListaDeIndividuos(lista);
		populacao.ordenar();
		for (Individuo individuo : populacao.getListaDeIndividuos()) {
			System.out.println(individuo.imprimirAdequacaoCusto());
		}
		populacao.salvarPopulacaoNoArquivo("solucoes.dat");
	}

}

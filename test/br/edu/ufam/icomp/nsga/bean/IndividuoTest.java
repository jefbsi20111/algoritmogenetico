package br.edu.ufam.icomp.nsga.bean;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IndividuoTest {

	private Individuo individuo;
	private Cromossomo tecnica;
	private Cromossomo analista;
	private Cromossomo informante;

	@Before
	public void init() {
		tecnica = new CromossomoTecnica(Arrays.asList(new Boolean[] { true,
				false, true, false, true, false, true, false, true, false,
				true, false, true, false, true }));
		analista = new CromossomoAnalista(Arrays.asList(new Boolean[] { true,
				false, true }));
		informante = new CromossomoInformante(Arrays.asList(new Boolean[] {
				true, true, true }));

		individuo = new Individuo(Arrays.asList(tecnica, analista, informante));
	}

	@Test
	public void deveDevolverOCustoDaTecnica() {
		Assert.assertEquals(79, tecnica.getCusto());
	}

	@Test
	public void deveDevolverAAdequacaoDaTecnica() {
		Assert.assertEquals(1650
				, tecnica.getAdequacao(tecnica));
	}

	@Test
	public void deveDevolverOCustoDoAnalista() {
		Assert.assertEquals(9, analista.getCusto());
	}

	@Test
	public void deveDevolverAAdequacaoDoAnalista() {
		Assert.assertEquals(2000, analista.getAdequacao(tecnica));
	}

	@Test
	public void deveDevolverOCustoDoInformante() {
		Assert.assertEquals(12, informante.getCusto());
	}

	@Test
	public void deveDevolverAAdequacaoDoInformante() {
		Assert.assertEquals(2330, informante.getAdequacao(tecnica));
	}

	@Test
	public void deveDevolverOCustoDaSolucao() {
		Assert.assertEquals(1659, individuo.getCusto());
	}


	@Test
	public void deveDevolverAAdequacaoDaSolucao() {
		Assert.assertEquals(5980, individuo.getAdequacao());
	}
	
	@Test
	public void deveCarregar10IndividuosDoArquivo(){
		List<Individuo> lista = Individuo.convertFromFile("amostra10.dat");
		for (Individuo individuo : lista) {
			System.out.println(individuo);
		}
		Assert.assertEquals(10, lista.size());
	}
}

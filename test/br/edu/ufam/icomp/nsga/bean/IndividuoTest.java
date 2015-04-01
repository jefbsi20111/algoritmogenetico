package br.edu.ufam.icomp.nsga.bean;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SolucaoTest {

	private Solucao solucao;
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

		solucao = new Solucao(Arrays.asList(tecnica, analista, informante));
	}

	@Test
	public void deveDevolverOCustoDaTecnica() {
		Assert.assertEquals(79, tecnica.getCusto());
	}

	@Test
	public void deveDevolverAAdequacaoDaTecnica() {
		Assert.assertEquals(825, tecnica.getAdequacao(tecnica));
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
		Assert.assertEquals(1659, solucao.getCusto());
	}


	@Test
	public void deveDevolverAAdequacaoDaSolucao() {
		Assert.assertEquals(5155, solucao.getAdequacao());
	}
}

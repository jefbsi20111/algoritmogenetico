package br.edu.ufam.icomp.nsga.bean;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.ufam.icomp.nsga.enums.TipoCromossomo;

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
	public void testSubstituirCromossomo() {
		Cromossomo tecnica = new CromossomoTecnica(Arrays.asList(new Boolean[] {
				false, true, false, true, true, false, true, false, true,
				false, true, false, true, false, true }));
		Cromossomo analista = new CromossomoAnalista(
				Arrays.asList(new Boolean[] { true, false, true }));
		Cromossomo informante = new CromossomoInformante(
				Arrays.asList(new Boolean[] { true, true, true }));

		Individuo local = new Individuo(Arrays.asList(tecnica, analista,
				informante));
		Assert.assertNotEquals(local, individuo);
		/*
		 * System.out.println(individuo); System.out.println(local);
		 * System.out.println("------");
		 */

		Cromossomo aux = local.getCromossomo(TipoCromossomo.TECNICA);
		local.substituirCromossomo(individuo
				.getCromossomo(TipoCromossomo.TECNICA));
		individuo.substituirCromossomo(aux);

		/*
		 * System.out.println(individuo); System.out.println(local);
		 */
		Assert.assertNotEquals(local, individuo);
	}

	@Test
	public void testClone() {
		Individuo individuo2 = (Individuo) individuo.clone();
		Assert.assertEquals(individuo, individuo2);
		Cromossomo c = individuo2.getCromossomo(TipoCromossomo.TECNICA);
		c.getListaDeGenes().set(0, false);
		Assert.assertNotEquals(individuo, individuo2);
	}

	@Test
	public void deveDevolverOCustoDaTecnica() {
		Assert.assertEquals(79, tecnica.getCusto());
	}

	@Test
	public void deveDevolverAAdequacaoDaTecnica() {
		Assert.assertEquals(1650, tecnica.getAdequacao(tecnica));
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
		// System.out.println(individuo.getGenes());
	}

	@Test
	public void deveCarregar10IndividuosDoArquivo() {
		List<Individuo> lista = Individuo.convertFromFile("amostra10.dat");
		/*
		 * for (Individuo individuo : lista) { System.out.println(individuo); }
		 */
		Assert.assertEquals(10, lista.size());
	}

	@Test
	public void deveOrdenarElementos() {
		List<Individuo> lista = Individuo.convertFromFile("amostra50.dat");

		for (Individuo individuo : lista) {
			System.out.println(individuo.imprimirAdequacaoCusto());
		}

		Assert.assertEquals(50, lista.size());
	}
}

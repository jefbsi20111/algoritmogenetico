package br.edu.ufam.icomp.nsga.bean;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class CromossomoTest {

	@Test
	public void testEqualsObject() {
		Cromossomo tecnica = new CromossomoTecnica(Arrays.asList(new Boolean[] {
				true, false, true, }));
		Cromossomo tec2 = (Cromossomo) tecnica.clone();
		
		Assert.assertEquals(tecnica, tec2);
		//System.out.println(tecnica.equals(tec2));
		
		tec2.getListaDeGenes().set(0, false);
		Assert.assertNotEquals(tecnica, tec2);
		//System.out.println(tecnica.equals(tec2));
	}

}

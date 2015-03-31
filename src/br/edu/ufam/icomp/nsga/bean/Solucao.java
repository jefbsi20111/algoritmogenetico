package br.edu.ufam.icomp.nsga.bean;

import java.util.List;

public class Solucao {
	private List<Cromossomo> listaDeCromossomos;

	public Solucao(List<Cromossomo> listaDeCromossomos) {
		super();
		this.listaDeCromossomos = listaDeCromossomos;
	}

	public int getCusto() {
		Cromossomo tecnica = getCromossomo(TipoCromossomo.TECNICA);
		Cromossomo analista = getCromossomo(TipoCromossomo.ANALISTA);
		Cromossomo informante = getCromossomo(TipoCromossomo.INFORMANTE);
		return tecnica.getCusto()
				* (analista.getCusto() + informante.getCusto());
	}

	public int getAdequacao() {
		int total = 0;
		Cromossomo tecnica = getCromossomo(TipoCromossomo.TECNICA);
		for (Cromossomo cromossomo : listaDeCromossomos) {
			total+=cromossomo.getAdequacao(tecnica);
		}
		return total;
	}

	private Cromossomo getCromossomo(TipoCromossomo tipo) {
		for (Cromossomo cromossomo : listaDeCromossomos) {
			if (cromossomo.getTipoCromossomo() == tipo) {
				return cromossomo;
			}
		}
		return null;
	}
}

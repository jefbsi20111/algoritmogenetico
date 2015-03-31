package br.edu.ufam.icomp.nsga.bean;

import java.util.List;

public class CromossomoAnalista extends Cromossomo {

	private Integer[][] matrizDeAdequacaoAnalista;

	public CromossomoAnalista(List<Boolean> listaDeGenes) {
		super(TipoCromossomo.ANALISTA, listaDeGenes);
		carregarAdquacao();
	}

	private void carregarAdquacao() {
		matrizDeAdequacaoAnalista = new Integer[][] {
				{ 140, 140, 110, 110, 140, 140, 140, 140, 110, 110, 140, 110, 140, 140, 110 },
				{ 60, 60, 70, 15, 100, 10, 15, 60, 110, 55, 100, 30, 30, -25, 30 },
				{ 140, 140, 110, 110, 140, 100, 110, 100, 110, 110, 140, 110, 110, 100, 110 } };
	}

	@Override
	public int getCusto() {
		return getCusto(getListaDeCustoAnalista());
	}

	public int getAdequacao(Cromossomo tecnica) {
		int total = 0;
		for (int i = 0; i < Cromossomo.getListaDeCustoAnalista().size(); i++) {
			for (int j = 0; j < Cromossomo.getListaDeCustoTecnica().size(); j++) {
				if (tecnica.getListaDeGenes().get(j)
						&& getListaDeGenes().get(i)) {
					total += matrizDeAdequacaoAnalista[i][j];
				}
			}
		}
		return total;
	}

	public Integer[][] getMatrizDeAdequacaoAnalista() {
		return matrizDeAdequacaoAnalista;
	}

}

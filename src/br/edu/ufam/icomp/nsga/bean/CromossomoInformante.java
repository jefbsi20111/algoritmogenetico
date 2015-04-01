package br.edu.ufam.icomp.nsga.bean;

import java.util.List;

import br.edu.ufam.icomp.nsga.enums.TipoCromossomo;

public class CromossomoInformante extends Cromossomo {
	
	private Integer[][] matrizDeAdequacaoInformante;

	public CromossomoInformante(List<Boolean> listaDeGenes) {
		super(TipoCromossomo.INFORMANTE,listaDeGenes);
		carregarAdquacao();
	}

	private void carregarAdquacao() {
		matrizDeAdequacaoInformante = new Integer[][] {
				{115, 115, 90, 115, 115, 95, 115, 75, 100, 75, 90, 115, 90, 75, 115},
				{115, 115, 70, 115, 115, 95, 115, 75, 100, 75, 90, 115, 90, 75, 115},
				{75,  75,  90, 115, 115, 65, 115, 45, 70,  55, 70, 115, 60, 45, 95}
			};
	}

	@Override
	public int getCusto() {
		return getCusto(getListaDeCustoInformante());
	}

	@Override
	public int getAdequacao(Cromossomo tecnica) {
		int total = 0;
		for (int i = 0; i < Cromossomo.getListaDeCustoInformante().size(); i++) {
			for (int j = 0; j < Cromossomo.getListaDeCustoTecnica().size(); j++) {
				if (tecnica.getListaDeGenes().get(j)
						&& getListaDeGenes().get(i)) {
					total += matrizDeAdequacaoInformante[i][j];
				}
			}
		}
		return total;
	}

	public Integer[][] getMatrizDeAdequacaoAnalista() {
		return matrizDeAdequacaoInformante;
	}


}

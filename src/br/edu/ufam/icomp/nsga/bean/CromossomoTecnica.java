package br.edu.ufam.icomp.nsga.bean;

import java.util.Arrays;
import java.util.List;

public class CromossomoTecnica extends Cromossomo {
	
	private static List<Integer> listaDeAdequacao;
	
	static{
		listaDeAdequacao = Arrays.asList(new Integer[] { 125, 100, 125, 50, 100, 90, 25, 100, 100, 100, 125, 100, 125, 90, 100});
	}

	public CromossomoTecnica(List<Boolean> listaDeGenes) {
		super(TipoCromossomo.TECNICA,listaDeGenes);
	}

	@Override
	public int getCusto() {
		return getCusto(getListaDeCustoTecnica());
	}

	@Override
	public int getAdequacao(Cromossomo tecnica) {
		int total = 0;
		for (int i=0; i<getListaDeGenes().size(); i++){
			if(getListaDeGenes().get(i)){
				total+=listaDeAdequacao.get(i);
			}
		}
		return total;
	}

	public static List<Integer> getListaDeAdequacao() {
		return listaDeAdequacao;
	}

}

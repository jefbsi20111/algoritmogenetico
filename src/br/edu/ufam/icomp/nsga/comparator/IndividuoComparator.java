package br.edu.ufam.icomp.nsga.comparator;

import java.util.Comparator;

import br.edu.ufam.icomp.nsga.bean.Individuo;

public class IndividuoComparator implements Comparator<Individuo>{

	@Override
	public int compare(Individuo ind1, Individuo ind2) {
		if(ind1.getAdequacao()>ind2.getAdequacao()){
			return -1;
		}else if(ind1.getAdequacao()<ind2.getAdequacao()){
			return 1;
		}else if(ind1.getCusto()<ind2.getCusto()){
			return -1;
		}else if(ind1.getCusto()>ind2.getCusto()){
			return 1;
		}
		return 0;
	}

}

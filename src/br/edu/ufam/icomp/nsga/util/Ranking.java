package br.edu.ufam.icomp.nsga.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import br.edu.ufam.icomp.nsga.bean.Populacao;
import br.edu.ufam.icomp.nsga.comparator.DominanceComparator;

public class Ranking {
	private Populacao conjuntoSolucao;
	private Populacao[] ranking;
	private static final Comparator dominance_ = new DominanceComparator();

	public Ranking(Populacao populacao) {
		conjuntoSolucao = populacao;
		// dominateMe[i] contains the number of solutions dominating i
		int[] dominateMe = new int[conjuntoSolucao.size()];

		// iDominate[k] contains the list of solutions dominated by k
		List<Integer>[] iDominate = new List[conjuntoSolucao.size()];

		// front[i] contains the list of individuals belonging to the front i
		List<Integer>[] front = new List[conjuntoSolucao.size() + 1];

		// flagDominate is an auxiliar encodings.variable
		int flagDominate;

		// Initialize the fronts
		for (int i = 0; i < front.length; i++)
			front[i] = new LinkedList<Integer>();

		// -> Fast non dominated sorting algorithm
		// Contribution of Guillaume Jacquenot
		for (int p = 0; p < conjuntoSolucao.size(); p++) {
			// Initialize the list of individuals that i dominate and the number
			// of individuals that dominate me
			iDominate[p] = new LinkedList<Integer>();
			dominateMe[p] = 0;
		}

		for (int p = 0; p < (conjuntoSolucao.size() - 1); p++) {
			// For all q individuals , calculate if p dominates q or vice versa
			for (int q = p + 1; q < conjuntoSolucao.size(); q++) {

				flagDominate = dominance_.compare(conjuntoSolucao.get(p),
						conjuntoSolucao.get(q));
				if (flagDominate == -1) {
					iDominate[p].add(q);
					dominateMe[q]++;
				} else if (flagDominate == 1) {
					iDominate[q].add(p);
					dominateMe[p]++;
				}
			}
			// If nobody dominates p, p belongs to the first front
		}

		for (int p = 0; p < conjuntoSolucao.size(); p++) {
			if (dominateMe[p] == 0) {
				front[0].add(p);
				conjuntoSolucao.getListaDeIndividuos().get(p).setRank(0);
			}
		}

		// Obtain the rest of fronts
		int i = 0;
		Iterator<Integer> it1, it2; // Iterators
		while (front[i].size() != 0) {
			i++;
			it1 = front[i - 1].iterator();
			while (it1.hasNext()) {
				it2 = iDominate[it1.next()].iterator();
				while (it2.hasNext()) {
					int index = it2.next();
					dominateMe[index]--;
					if (dominateMe[index] == 0) {
						front[i].add(index);
						conjuntoSolucao.getListaDeIndividuos().get(index)
								.setRank(i);
					}
				}
			}
		}

		ranking = new Populacao[i];
		// 0,1,2,....,i-1 are front, then i fronts
		for (int j = 0; j < i; j++) {
			ranking[j] = new Populacao(front[j].size());
			it1 = front[j].iterator();
			while (it1.hasNext()) {
				ranking[j]
						.add(populacao.getListaDeIndividuos().get(it1.next()));
			}
		}
	}

	/**
	 * Returns a <code>SolutionSet</code> containing the solutions of a given
	 * rank.
	 * 
	 * @param rank
	 *            The rank
	 * @return Object representing the <code>SolutionSet</code>.
	 */
	public Populacao getSubfront(int rank) {
		return ranking[rank];
	} // getSubFront

	public Populacao[] getRanking() {
		return ranking;
	}

	/**
	 * Returns the total number of subFronts founds.
	 */
	public int getNumberOfSubfronts() {
		return ranking.length;
	} // getNumberOfSubfronts
}

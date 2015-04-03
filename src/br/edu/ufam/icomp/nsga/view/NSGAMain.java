package br.edu.ufam.icomp.nsga.view;

import java.util.List;

import br.edu.ufam.icomp.nsga.bean.Distance;
import br.edu.ufam.icomp.nsga.bean.Individuo;
import br.edu.ufam.icomp.nsga.bean.Populacao;
import br.edu.ufam.icomp.nsga.comparator.CrowdingComparator;
import br.edu.ufam.icomp.nsga.util.Ranking;

public class NSGAMain {

	private static int TAMANHO_DA_POPULACAO = 0;
	private static final int QUANTIDADE_MAXIMA_TECNICAS = 6;
	private static final int QUANTIDADE_MAXIMA_ANALISTAS = 3;
	private static final int QUANTIDADE_MAXIMA_INFORMANTES = 3;

	private static final int NUMERO_DE_OBJETIVOS = 2;

	private static final int QUANTIDADE_MAXIMA_AVALIACOES = 33000;

	public static void main(String[] args) {
		for(int amostra=1; amostra<=4; amostra++){
			
			TAMANHO_DA_POPULACAO += 50;
			
			int quantidadeDeAvaliacoes = 0;
			Populacao populacao = new Populacao(TAMANHO_DA_POPULACAO);
			Populacao descendencia = null;
			Populacao uniao = null;

			Distance distance = new Distance();

			// Criação da população inicial
			System.out.println("Criação da população inicial");
			populacao.setListaDeIndividuos((Populacao.criarPopulacao(
					TAMANHO_DA_POPULACAO, QUANTIDADE_MAXIMA_TECNICAS,
					QUANTIDADE_MAXIMA_ANALISTAS, QUANTIDADE_MAXIMA_INFORMANTES)));
			while (quantidadeDeAvaliacoes < QUANTIDADE_MAXIMA_AVALIACOES) {
				descendencia = new Populacao();
				for (int i = 0; i < (populacao.size() / 2); i++) {
					if (quantidadeDeAvaliacoes < QUANTIDADE_MAXIMA_AVALIACOES) {
						Individuo individuo1 = populacao.selecao();
						Individuo individuo2 = populacao.selecao();

						List<Individuo> listaDePais = populacao.cruzamento(
								individuo1, individuo2);
						// populacao.mutacao(listaDePais.get(0));
						// populacao.mutacao(listaDePais.get(1));

						// populacao.avaliacao(listaDePais.get(0));
						// populacao.avaliacao(listaDePais.get(1));
						descendencia.addAll(listaDePais);
						quantidadeDeAvaliacoes += 2;
					}// if
				}// for

				uniao = new Populacao(populacao.size()+descendencia.size());
				uniao.addAll(populacao.getListaDeIndividuos());
				uniao.addAll(descendencia.getListaDeIndividuos());

				Ranking ranking = new Ranking(uniao);

				int remain = TAMANHO_DA_POPULACAO;
				int index = 0;
				Populacao front = null;
				populacao.clear();

				// Obtain the next front
				front = ranking.getSubfront(index);

				while ((remain > 0) && (remain >= front.size())) {
					// Assign crowding distance to individuals
					distance.crowdingDistanceAssignment(front, NUMERO_DE_OBJETIVOS);
					// Add the individuals of this front
					for (int k = 0; k < front.size(); k++) {
						populacao.add(front.getListaDeIndividuos().get(k));
					} // for

					// Decrement remain
					remain = remain - front.size();

					// Obtain the next front
					index++;
					if (remain > 0 && index<ranking.getRanking().length) {
						front = ranking.getSubfront(index);
					} // if
				} // while

				// Remain is less than front(index).size, insert only the best one
				if (remain > 0) { // front contains individuals to insert
					distance.crowdingDistanceAssignment(front,2);
					front.ordenar(new CrowdingComparator());
					for (int k = 0; k < remain; k++) {
						populacao.add(front.get(k));
					} // for

					remain = 0;
				} // if
				
				// This piece of code shows how to use the indicator object into the code
			      // of NSGA-II. In particular, it finds the number of evaluations required
			      // by the algorithm to obtain a Pareto front with a hypervolume higher
			      // than the hypervolume of the true Pareto front.
			      /*if ((indicators != null) &&
			          (requiredEvaluations == 0)) {
			        double HV = indicators.getHypervolume(population);
			        if (HV >= (0.98 * indicators.getTrueParetoFrontHypervolume())) {
			          requiredEvaluations = evaluations;
			        } // if
			      }*/ // if
			}

			Ranking ranking = new Ranking(populacao);
			Populacao resultado = ranking.getSubfront(0);
			
			System.out.println("Gerar arquivo final");
			resultado.salvarPopulacaoNoArquivo("/Users/marciopalheta/Dropbox/ResultadoExperimento/solucao_nsga_"+TAMANHO_DA_POPULACAO);
			resultado.salvarPopulacaoNoArquivo("solucao_nsga_"+TAMANHO_DA_POPULACAO);
		}
	}
}

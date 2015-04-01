package br.edu.ufam.icomp.nsga.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.ufam.icomp.nsga.util.Util;

public class Populacao {
	private List<Individuo> listaDeIndividuos = new ArrayList<Individuo>();
	private int [] vetorPosicoesAleatorias;
	private int indexVetorPosicoesAleatorias;

	/**
	 * Método de criação da população inicial
	 * 
	 * @param tamanhoDaPopulacao
	 * @param maximoDeTecnicas
	 * @param maximoDeAnalistas
	 * @param maximoDeInformantes
	 * @return lista com indivíduos iniciai da população
	 */
	public static List<Individuo> criarPopulacao(int tamanhoDaPopulacao,
			int maximoDeTecnicas, int maximoDeAnalistas, int maximoDeInformantes) {
		List<Individuo> lista = new ArrayList<Individuo>();
		for (int i = 0; i < tamanhoDaPopulacao; i++) {
			Cromossomo tecnica = gerarTecnica(maximoDeTecnicas);
			Cromossomo analista = gerarAnalista(maximoDeAnalistas);
			Cromossomo informante = gerarInformante(maximoDeInformantes);
			lista.add(new Individuo(Arrays
					.asList(tecnica, analista, informante)));
		}
		return lista;
	}

	public Individuo selecao() {
		if(vetorPosicoesAleatorias==null){
			vetorPosicoesAleatorias = Util.intPermutation(listaDeIndividuos.size());
		}
		Individuo individuo1 = listaDeIndividuos.get(vetorPosicoesAleatorias[indexVetorPosicoesAleatorias]);
		Individuo individuo2 = listaDeIndividuos.get(vetorPosicoesAleatorias[indexVetorPosicoesAleatorias+1]);
		System.out.println("selecionados: "+individuo1+individuo2);
		
		indexVetorPosicoesAleatorias = (indexVetorPosicoesAleatorias + 2) % listaDeIndividuos.size();

		return selecaoPar(individuo1, individuo2);
	}

	private Individuo selecaoPar(Individuo individuo1, Individuo individuo2) {
		if (individuo1.getAdequacao() > individuo2.getAdequacao()) {
			return individuo1;
		} else if (individuo1.getAdequacao() < individuo2.getAdequacao()) {
			return individuo2;
		} else if (individuo1.getCusto() < individuo2.getCusto()) {
			return individuo1;
		} else {
			return individuo2;
		}
	}

	public void cruzamento() {

	}

	public void mutacao() {

	}

	private static Cromossomo gerarInformante(int maximoDeInformantes) {
		int tamanho = Cromossomo.getListaDeCustoInformante().size();
		Boolean[] genes = new Boolean[tamanho];

		for (int i = 0; i < maximoDeInformantes; i++) {
			genes[Util.sorteio(Util.LIMITE_INFERIOR_VETOR, tamanho)] = true;
		}

		for (int i = 0; i < tamanho; i++) {
			if (genes[i] == null) {
				genes[i] = false;
			}
		}
		return new CromossomoInformante(Arrays.asList(genes));
	}

	private static Cromossomo gerarAnalista(int maximoDeAnalistas) {
		int tamanho = Cromossomo.getListaDeCustoAnalista().size();
		Boolean[] genes = new Boolean[tamanho];

		for (int i = 0; i < maximoDeAnalistas; i++) {
			genes[Util.sorteio(Util.LIMITE_INFERIOR_VETOR, tamanho)] = true;
		}

		for (int i = 0; i < tamanho; i++) {
			if (genes[i] == null) {
				genes[i] = false;
			}
		}
		return new CromossomoAnalista(Arrays.asList(genes));
	}

	private static Cromossomo gerarTecnica(int maximoDeTecnicas) {
		int tamanho = Cromossomo.getListaDeCustoTecnica().size();
		Boolean[] genes = new Boolean[tamanho];

		for (int i = 0; i < maximoDeTecnicas; i++) {
			genes[Util.sorteio(Util.LIMITE_INFERIOR_VETOR, tamanho)] = true;
		}

		for (int i = 0; i < tamanho; i++) {
			if (genes[i] == null) {
				genes[i] = false;
			}
		}
		return new CromossomoTecnica(Arrays.asList(genes));
	}

	public List<Individuo> getListaDeIndividuos() {
		return listaDeIndividuos;
	}

	public void setListaDeIndividuos(List<Individuo> listaDeIndividuos) {
		this.listaDeIndividuos = listaDeIndividuos;
	}
}

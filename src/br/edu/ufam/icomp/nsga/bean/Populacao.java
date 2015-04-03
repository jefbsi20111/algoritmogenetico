package br.edu.ufam.icomp.nsga.bean;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.edu.ufam.icomp.nsga.comparator.DominanceComparator;
import br.edu.ufam.icomp.nsga.comparator.IndividuoComparator;
import br.edu.ufam.icomp.nsga.enums.TipoCromossomo;
import br.edu.ufam.icomp.nsga.util.PseudoRandom;
import br.edu.ufam.icomp.nsga.util.Util;

public class Populacao {
	private List<Individuo> listaDeIndividuos = new ArrayList<Individuo>();
	private int[] vetorPosicoesAleatorias;
	private int indexVetorPosicoesAleatorias;
	private int capacidade;

	private Comparator dominance = new DominanceComparator();;

	public Populacao() {
	}

	public Populacao(int capacidade) {
		this.capacidade = capacidade;
	}

	public void clear() {
		listaDeIndividuos.clear();
	}

	public Individuo get(int index) {
		return listaDeIndividuos.get(index);
	}

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

	public void ordenar() {
		Collections.sort(listaDeIndividuos, new IndividuoComparator());
	}

	public void ordenar(Comparator comparator) {
		Collections.sort(listaDeIndividuos, comparator);
	}

	public void salvarPopulacaoNoArquivo(String arquivo) {
		OutputStream saida;
		try {
			saida = new FileOutputStream(arquivo);
			OutputStreamWriter writer = new OutputStreamWriter(saida);
			BufferedWriter buffer = new BufferedWriter(writer);
			if (listaDeIndividuos.size() > 0) {
				for (int i = 0; i < listaDeIndividuos.size(); i++) {
					int adequacao = listaDeIndividuos.get(i).getAdequacao();
					int custo = listaDeIndividuos.get(i).getCusto();
					buffer.write("" + adequacao + " " + custo + " ");
					buffer.newLine();
				}
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void addAll(List<Individuo> novos) {
		listaDeIndividuos.addAll(novos);
	}

	public void add(Individuo individuo) {
		if (capacidade != listaDeIndividuos.size()
				&& !listaDeIndividuos.contains(individuo)) {
			listaDeIndividuos.add(individuo);
		}
	}

	public int size() {
		return listaDeIndividuos.size();
	}

	public Individuo selecao() {
		if (indexVetorPosicoesAleatorias == 0) {
			vetorPosicoesAleatorias = Util.intPermutation(listaDeIndividuos
					.size());
		}
		/*
		 * Individuo individuo1 = listaDeIndividuos
		 * .get(vetorPosicoesAleatorias[indexVetorPosicoesAleatorias]);
		 * Individuo individuo2 = listaDeIndividuos
		 * .get(vetorPosicoesAleatorias[indexVetorPosicoesAleatorias + 1]);
		 */

		Individuo individuo1 = listaDeIndividuos.get(PseudoRandom.randInt(0,
				listaDeIndividuos.size() - 1));
		Individuo individuo2 = listaDeIndividuos.get(PseudoRandom.randInt(0,
				listaDeIndividuos.size() - 1));
		System.out.println("selecionados: " + individuo1 + individuo2);

		indexVetorPosicoesAleatorias = (indexVetorPosicoesAleatorias + 2)
				% listaDeIndividuos.size();

		// index_ = (index_ + 2) % population.size();

		int flag = dominance.compare(individuo1, individuo2);
		if (flag == -1)
			return individuo1;
		else if (flag == 1)
			return individuo2;
		else if (individuo1.getCrowdingDistance() > individuo2
				.getCrowdingDistance())
			return individuo1;
		else if (individuo2.getCrowdingDistance() > individuo1
				.getCrowdingDistance())
			return individuo2;
		else if (PseudoRandom.randDouble() < 0.5)
			return individuo1;
		else
			return individuo2;
	}

	public List<Individuo> cruzamento(Individuo individuo1, Individuo individuo2) {

		Individuo ind1 = (Individuo) individuo1.clone();
		Individuo ind2 = (Individuo) individuo2.clone();
		List<Individuo> descendentes = Arrays.asList(ind1, ind2);

		Cromossomo aux = ind1.getCromossomo(TipoCromossomo.TECNICA);
		Cromossomo c2 = ind2.getCromossomo(TipoCromossomo.TECNICA);
		ind1.substituirCromossomo(c2);
		ind2.substituirCromossomo(aux);

		return descendentes;
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

	public int getCapacidade() {
		return capacidade;
	}
}

package br.edu.ufam.icomp.nsga.bean;

import java.util.Arrays;
import java.util.List;

public abstract class Cromossomo {
	private List<Boolean> listaDeGenes;
	private static List<Integer> listaDeCustoTecnica;
	private static List<Integer> listaDeCustoAnalista;
	private static List<Integer> listaDeCustoInformante;
	private TipoCromossomo tipoCromossomo;

	static {
		listaDeCustoTecnica = Arrays.asList(new Integer[] { 4, 6, 6, 8, 12, 12,
				15, 5, 4, 15, 15, 25, 8, 10, 15 });
		listaDeCustoAnalista = Arrays.asList(new Integer[] { 5, 3, 4 });
		listaDeCustoInformante = Arrays.asList(new Integer[] { 4, 5, 3 });
	}

	public Cromossomo(TipoCromossomo tipoCromossomo, List<Boolean> listaDeGenes) {
		super();
		this.listaDeGenes = listaDeGenes;
		this.tipoCromossomo = tipoCromossomo;
	}

	public abstract int getCusto();

	public abstract int getAdequacao(Cromossomo tecnica);

	public int getCusto(List<Integer> listaDeCustos) {
		Integer custo = 0;
		for (int i = 0; i < listaDeGenes.size(); i++) {
			Boolean ocorre = listaDeGenes.get(i);
			if (ocorre) {
				custo += listaDeCustos.get(i);
			}
		}
		return custo;
	}

	public List<Boolean> getListaDeGenes() {
		return listaDeGenes;
	}

	public void setListaDeGenes(List<Boolean> listaDeGenes) {
		this.listaDeGenes = listaDeGenes;
	}

	public static List<Integer> getListaDeCustoAnalista() {
		return listaDeCustoAnalista;
	}

	public static List<Integer> getListaDeCustoInformante() {
		return listaDeCustoInformante;
	}

	public static List<Integer> getListaDeCustoTecnica() {
		return listaDeCustoTecnica;
	}

	public TipoCromossomo getTipoCromossomo() {
		return tipoCromossomo;
	}

	public void setTipoCromossomo(TipoCromossomo tipoCromossomo) {
		this.tipoCromossomo = tipoCromossomo;
	}

}

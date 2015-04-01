package br.edu.ufam.icomp.nsga.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.ufam.icomp.nsga.enums.TipoCromossomo;
import br.edu.ufam.icomp.nsga.util.Util;

public class Individuo {
	private List<Cromossomo> listaDeCromossomos;
	
	@Override
	public String toString() {
		return listaDeCromossomos.toString();
	}

	public Individuo(List<Cromossomo> listaDeCromossomos) {
		super();
		this.listaDeCromossomos = listaDeCromossomos;
	}
	
	public static List<Individuo> convertFromFile(String path){
		List<Individuo> resultado = new ArrayList<Individuo>();
		BufferedReader buffer = new BufferedReader(Util.getConteudoArquivo(path));
		try {
			String linha = buffer.readLine();
			while(linha != null){
				resultado.add(convertFromString(linha));
				linha = buffer.readLine();
			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	private static Individuo convertFromString(String linha) {
		linha = linha.replace("[", "").replace("]", "").replace(", ", "").replace(",", "");
		String [] tokens = linha.split(" ");
		int tamanhoTecnica = Cromossomo.getListaDeCustoTecnica().size();
		int tamanhoAnalista = Cromossomo.getListaDeCustoAnalista().size();
		int tamanhoInformante = Cromossomo.getListaDeCustoInformante().size();
		
		List<Boolean>listaDeGenes = new ArrayList<Boolean>();
		for(int i=0; i<tamanhoTecnica; i++){
			if(tokens[i].equals("0")){
				listaDeGenes.add(false);
			}else{
				listaDeGenes.add(true);
			}
		}	
		Cromossomo tecnica = new CromossomoTecnica(listaDeGenes);
		
		listaDeGenes = new ArrayList<Boolean>();
		for(int i=tamanhoTecnica; i<tamanhoTecnica+tamanhoAnalista; i++){
			if(tokens[i].equals("0")){
				listaDeGenes.add(false);
			}else{
				listaDeGenes.add(true);
			}
		}
		Cromossomo analista = new CromossomoAnalista(listaDeGenes);
		
		listaDeGenes = new ArrayList<Boolean>();
		for(int i=tamanhoTecnica+tamanhoAnalista; i<tamanhoTecnica+tamanhoAnalista+tamanhoInformante; i++){
			if(tokens[i].equals("0")){
				listaDeGenes.add(false);
			}else{
				listaDeGenes.add(true);
			}
		}
		Cromossomo informante = new CromossomoInformante(listaDeGenes);
		
		return new Individuo(Arrays.asList(tecnica, analista, informante));
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

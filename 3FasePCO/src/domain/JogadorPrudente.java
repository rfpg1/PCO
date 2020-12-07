package domain;

import java.util.ArrayList;
import java.util.List;

import utils.Par;

public class JogadorPrudente extends Jogador{
	
	private Direcionador direcionador;
	private int recursos;
	private ArrayList<Par<Integer, Integer>> registo; //(Recursos, pontos)

	public JogadorPrudente(int nLinhas, int nCols, List<Par<Integer, Integer>> obstaculos, int recursos, Direcionador dir) {
		super(nLinhas, nCols, obstaculos);
		this.direcionador = dir;
		this.recursos = recursos;
		this.registo = new ArrayList<>();
	}

	public int recursos() {
		return recursos;
	}

	@Override
	public String direcao() {
		int preco = direcionador.precoConsulta(this.alvoSimulacao());
		recursos -= preco;
		return direcionador.direcao(this.alvoSimulacao());
	}

	@Override
	public int forca() {
		return Math.max(this.ambiente.dimensoes().primeiro(), this.ambiente.dimensoes().segundo());
	}
	
	@Override
	public boolean podeAtuar() {
		if(!this.ambiente.podeAtuar()) {
			return false;
		}
		if(this.recursos < this.direcionador.precoConsulta(this.alvoSimulacao())) {
			return false;
		}
		int totalRecursos = getRecursos();
		int totalPontos = this.pontos;
		if(totalRecursos > totalPontos * this.ambiente.dimensoes().primeiro() * this.ambiente.dimensoes().segundo()) {
			return false;
		}
		return true;
	}


	private int getRecursos() {
		int total = 0;
		for(Par<Integer, Integer> par : this.registo) {
			total += par.primeiro();
		}
		return total;
	}

	@Override
	public void registaJogadaComPontuacao(List<Par<Integer, Integer>> afetados, int pontos) {
		super.registaJogadaComPontuacao(afetados, pontos);
		registo.add(new Par<Integer, Integer>(this.direcionador.precoConsulta(this.alvoSimulacao()), pontos));
	}
	
	public String toString() {
		StringBuilder bob = new StringBuilder();
		bob.append("Pontuacao: " + this.pontos + "\n");
		bob.append(this.ambiente.toString());
		String s = getClassName(this.direcionador.getClass().getName());
		bob.append("Direcionador: " + s + "\n");
		bob.append("Recurso: " + recursos + "\tGastos: " + getRecursos() + "\n");
		bob.append("Jogadas: \n");
		for(Par<Integer, Integer> par : registo) {
			bob.append("Gasto: " + par.primeiro() + "\tPontos obtidos: " + par.segundo() + "\n");
		}
		return bob.toString();
	}

	private String getClassName(String name) {
		StringBuilder bob = new StringBuilder();
		int index = 0;
		while(name.charAt(index) != '.') {
			index++;
		}
		bob.append(name.subSequence(index + 1, name.length()));
		return bob.toString();
	}

}

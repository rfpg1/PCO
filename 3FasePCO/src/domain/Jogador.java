package domain;

import java.util.List;

import utils.Par;

public abstract class Jogador implements Acionavel{
	
	protected Ambiente ambiente;
	protected int pontos;

	public Jogador(int nLinhas, int nCols, List<Par<Integer, Integer>> obstaculos) {
		this.ambiente = new Ambiente(nLinhas, nCols, obstaculos);
		pontos = 0;
	}
	
	public int potuacao() {
		return this.pontos;
	}
	
	public Par<Integer, Integer> dimensoesAmbiente(){
		return this.ambiente.dimensoes();
	}
	
	public void registaJogadaComPontuacao(List<Par<Integer, Integer>> afetados, int pontos) {
		ambiente.registaAfetados(afetados);
		this.pontos += pontos;
	}
	
	@Override
	public String toString() {
		StringBuilder bob = new StringBuilder();
		bob.append("Pontuacao: " + this.pontos + "\n");
		bob.append(this.ambiente.toString());
		return bob.toString();
	}
	
	@Override
	public EstadoSimulacao[][] alvoSimulacao(){
		return ambiente.alvoSimulacao();
	}
	
	@Override
	public boolean podeAtuar() {
		return ambiente.podeAtuar();
	}
	
	public abstract String direcao();
	
	public abstract int forca();
}

package domain;

import java.util.List;
import java.util.Random;

import utils.Par;

public class JogadorConfiante extends Jogador{
	
	private Random random;
	private final long SEED = 1;
	private final String[] DIRECOES = {"N", "S", "E", "O"};

	public JogadorConfiante(int nLinhas, int nCols, List<Par<Integer, Integer>> obstaculos) {
		super(nLinhas, nCols, obstaculos);
		this.random = new Random(SEED);
	}

	@Override
	public String direcao() {
		return DIRECOES[random.nextInt(DIRECOES.length)];
	}

	@Override
	public int forca() {
		return random.nextInt(this.ambiente.dimensoes().primeiro());
	}
	
}

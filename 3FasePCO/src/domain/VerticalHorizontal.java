package domain;

public class VerticalHorizontal implements Direcionador{

	@Override
	public String direcao(EstadoSimulacao[][] alvo) {
		
		EstadoSimulacao[] primeiraColuna = new EstadoSimulacao[alvo.length];
		EstadoSimulacao[] ultimaColuna   = new EstadoSimulacao[alvo.length];
		
		for (int i = 0; i < alvo.length; i++) {
			primeiraColuna[i] = alvo[i][0];
			ultimaColuna[i]   = alvo[i][alvo[0].length - 1];
		}
		
		int afetadosPrimeiraLinha  = getInfetados(alvo[0]);
		int afetadosUltimaLinha    = getInfetados(alvo[alvo.length - 1]);
		int afetadosPrimeiraColuna = getInfetados(primeiraColuna);
		int afetadosUltimaColuna   = getInfetados(ultimaColuna);
		
		if(Math.max(afetadosPrimeiraColuna, afetadosUltimaColuna) - Math.min(afetadosPrimeiraColuna, afetadosUltimaColuna) >= 
			Math.max(afetadosPrimeiraLinha, afetadosUltimaLinha) - Math.min(afetadosPrimeiraLinha, afetadosUltimaLinha)) {
			if(afetadosPrimeiraColuna > afetadosUltimaColuna) {
				return "O";
			} else {
				return "E";
			}
		} else {
			if(afetadosPrimeiraLinha > afetadosUltimaLinha) {
				return "N";
			} else {
				return "S";
			}
		}
	}

	private int getInfetados(EstadoSimulacao[] alvo) {
		int count = 0;
		for (int i = 0; i < alvo.length; i++) {
			if(alvo[i] == EstadoSimulacao.AFETADO) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int precoConsulta(EstadoSimulacao[][] alvo) {
		return 2 * alvo.length + 2 * alvo[0].length;
	}

}

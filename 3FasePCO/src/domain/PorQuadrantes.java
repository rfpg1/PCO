package domain;

public class PorQuadrantes implements Direcionador{
	
	@Override
	public String direcao(EstadoSimulacao[][] alvo) {
		
		int pQuadrante   = getQuadranteX(alvo.length - (alvo.length / 2), alvo[0].length - (alvo[0].length / 2), 
				0, alvo[0].length - (alvo[0].length / 2), alvo.length - (alvo.length / 2), alvo[0].length, alvo);
		
		int sQuadrante   = getQuadranteX(alvo.length - (alvo.length / 2), alvo[0].length / 2, 
				0, 0, alvo.length - (alvo.length / 2), alvo[0].length / 2, alvo);
		
		int tQuadrante   = getQuadranteX(alvo.length / 2, alvo[0].length / 2, 
				alvo.length - (alvo.length / 2), 0,	alvo.length, alvo[0].length / 2, alvo);
		
		int qQuadrante   = getQuadranteX(alvo.length / 2, alvo[0].length - (alvo[0].length / 2), 
												alvo.length - (alvo.length / 2), alvo[0].length - (alvo[0].length / 2),
												alvo.length, alvo[0].length, alvo);

		int[] values = {pQuadrante, sQuadrante, tQuadrante, qQuadrante};
		
		int min = minimo(values);
		if(min == pQuadrante) {
			if(Math.max(qQuadrante, sQuadrante) == pQuadrante) {
				return "O";
			} else {
				return "S";
			}
		} else if(min == sQuadrante) {
			if(Math.max(pQuadrante, tQuadrante) == pQuadrante) {
				return "E";
			} else {
				return "S";
			}
		} else if(min == tQuadrante) {
			if(Math.max(sQuadrante, qQuadrante) == sQuadrante) {
				return "N";
			} else {
				return "E";
			}			
		} else {
			if(Math.max(tQuadrante, pQuadrante) == pQuadrante) {
				return "N";
			} else {
				return "O";
			}		
		}
	}
	
	private int getQuadranteX(int linhas, int colunas, int inicioI, int inicioJ, int finalI, int finalJ, EstadoSimulacao[][] alvo) {
		EstadoSimulacao[][] init = new EstadoSimulacao[linhas][colunas];
		int index = 0;
		for (int i = inicioI; i < finalI; i++) {
			int count = 0;
			for (int j = inicioJ; j < finalJ; j++) {
				init[index][count] = alvo[i][j];
				count++;
			}
			index++;
		}
		EstadoSimulacao[] result = matrixToVetor(init);
		
		return getInfetados(result);
	}
	
	private int minimo(int[] values) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < values.length; i++) {
			if(min > values[i]) {
				min = values[i];
			}
		}
		return min;
	}

	private EstadoSimulacao[] matrixToVetor(EstadoSimulacao[][] alvo) {
		EstadoSimulacao[] result = new EstadoSimulacao[alvo.length * alvo[0].length];
		int index = 0;
		for (int i = 0; i < alvo.length; i++) {
			for (int j = 0; j < alvo[0].length; j++) {
				result[index] = alvo[i][j];
				index++;
			}
		}
		return result;
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
		return alvo.length * alvo[0].length;
	}
	
}

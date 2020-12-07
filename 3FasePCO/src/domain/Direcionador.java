package domain;

public interface Direcionador {
	public String direcao(EstadoSimulacao[][] alvo);
	
	public int precoConsulta(EstadoSimulacao[][] alvo);
}

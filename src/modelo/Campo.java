package modelo;

import java.util.ArrayList;
import java.util.List;

import excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;

	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;

	private List<Campo> vizinhos = new ArrayList<>();

	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	boolean adicionarVizinho(Campo vizinho) {
		boolean candidatoaLinha = linha != vizinho.linha;
		boolean candidatoaColuna = coluna != vizinho.coluna;
		boolean diagonal = candidatoaLinha && candidatoaColuna;

		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaResult = deltaColuna + deltaLinha;

		if (deltaResult == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if (deltaResult == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else
			return false;
	}

	void alternarMarcacao() {
		if (!aberto) {
			marcado = !marcado;
		}
	}

	boolean abrir() {
		if (!aberto && !marcado) {
			aberto = true;

			if (minado) {
				throw new ExplosaoException();
			}
			if (vizinhacaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}

			return true;
		} else
			return false;
	}

	boolean vizinhacaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	void minar() {
		minado = true;
	}

	public boolean isMarcar() {
		return marcado;
	}

	public boolean isAberto() {
		return aberto;
	}

	public boolean isFechado() {
		return !isAberto();
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = marcado && minado;

		return desvendado || protegido;
	}

	long minasNaVizinhaca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}

	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}

	public String toString() {
		if(marcado) {
			return "X";
		}else if (aberto && minado) {
			return "*";
		}else if (aberto && minasNaVizinhaca() > 0) {
			return Long.toString(minasNaVizinhaca());			
		}else if (aberto) {
			return " ";
		}else 
			return "?";
	}

}

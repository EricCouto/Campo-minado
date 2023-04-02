package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
	private int linhas;
	private int colunas;
	private int minas;

	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int linha, int coluna, int mina) {

		this.linhas = linha;
		this.colunas = coluna;
		this.minas = mina;

		gerarCampos();
		associarOsVizinhos();
		sortearAsMinas();

	}

	private void gerarCampos() {

		for (int l = 0; l < linhas; l++) {
			for (int c = 0; c < colunas; c++) {
				campos.add(new Campo(l, c));

			}

		}
	}

	private void associarOsVizinhos() {
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2);

			}

		}

	}

	private void sortearAsMinas() {
		// TODO Auto-generated method stub

	}

}

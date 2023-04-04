package campoMinado.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import campoMinado.excecao.ExplosaoException;

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
		associarVizinhos();
		sortearAsMinas();

	}

	public void abrir(int linha, int coluna) {
		try {
			campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
					.ifPresent(c -> c.abrir());

		} catch (ExplosaoException e) {

			campos.forEach(c -> c.setAberto(true));
			throw e;
		}
	}

	public void alternarMarcacao(int linha, int coluna) {
		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(c -> c.alternarMarcacao());

	}

	private void gerarCampos() {

		for (int l = 0; l < linhas; l++) {
			for (int c = 0; c < colunas; c++) {
				campos.add(new Campo(l, c));

			}

		}
	}

	private void associarVizinhos() {
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2);

			}

		}

	}

	private void sortearAsMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();
		do {
			minasArmadas = campos.stream().filter(minado).count();
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
		} while (minasArmadas < minas);
	}

	public boolean objetivoAlcancado() {

		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}

	public void reiniciar() {
		campos.forEach(c -> c.reiniciar());
		sortearAsMinas();

	}

	public String toString() {

		StringBuilder sb = new StringBuilder();
		int i = 0;

		for (int l = 0; l < linhas; l++) {
			for (int c = 0; c < colunas; c++) {

				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;

			}
			sb.append("\n");
		}

		return sb.toString();
	}

}

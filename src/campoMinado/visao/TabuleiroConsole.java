package campoMinado.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import campoMinado.excecao.ExplosaoException;
import campoMinado.excecao.SairException;
import campoMinado.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {

		this.tabuleiro = tabuleiro;

		executarJogo();

	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			while (continuar) {
				cicloDoJogo();

				System.out.println("outra partida? (S/n) ");
				String resposta = entrada.nextLine();

				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else
					tabuleiro.reiniciar();
			}

		} catch (Exception e) {
			System.out.println("tchau!!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);

				String digitado = capturarValorDigitado("digite (x / y): ");

				Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim()))
						.iterator();

//				System.out.println(xy.next());
//				System.out.println(xy.next());

				digitado = capturarValorDigitado("1- para abrir ou 2- para (des)marcar: ");
				if ("1".equalsIgnoreCase(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if ("2".equalsIgnoreCase(digitado)) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());

				}

			}

			System.out.println("vc ganhou");
		} catch (ExplosaoException e) {
			
			System.out.println(tabuleiro);
			System.out.println("vc perdeu!");
		}
	}

	private String capturarValorDigitado(String texto) {
		System.out.println(texto);
		String digitado = entrada.nextLine();

		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}

		return digitado;
	}

}

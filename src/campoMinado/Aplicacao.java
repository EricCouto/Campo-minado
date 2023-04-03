package campoMinado;

import campoMinado.modelo.Tabuleiro;
import campoMinado.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		new  TabuleiroConsole(tabuleiro);

// 		tabuleiro.abrir(1, 3);
//		tabuleiro.alternarMarcacao(4, 4);
//		tabuleiro.alternarMarcacao(4, 5);

		System.out.println(tabuleiro);
	}

}

package view;

import java.io.IOException;

import controle.ManipuladorArquivos;

public class App {
	public static void main(String[] args) throws IOException {
		long tempoInicio = System.currentTimeMillis();
		{
			ManipuladorArquivos manipulador = new ManipuladorArquivos();
			manipulador.setDiretorioDadosMercado("DadosMercado.csv");
			manipulador.setDiretorioDadosOperacao("Operacoes.csv");
			manipulador.lerMercado();
			manipulador.lerOperacao();
			manipulador.gerarArquivoResultado();
		}
		System.out.println("Tempo Execução\nSegundos: "+(System.currentTimeMillis()-tempoInicio)/1000);
	}
}

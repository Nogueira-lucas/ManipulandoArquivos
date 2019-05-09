package controle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import modelo.Mercado;
import modelo.Operacao;
import modelo.Resultado;

public class ManipuladorArquivos {
	
	private String diretorioDadosMercado;
	private String diretorioDadosOperacao;
	private List<Mercado> mercados = null;
	private List<Operacao> operacoes = null;
	private FileWriter escritor;
	private Resultado resultado;
	
	public ManipuladorArquivos() {}
	
	public ManipuladorArquivos(String diretorioDadosMercado, String diretorioDadosOperacao) {
		super();
		this.diretorioDadosMercado = diretorioDadosMercado;
		this.diretorioDadosOperacao = diretorioDadosOperacao;
	}
	
	public String getDiretorioDadosMercado() {
		return diretorioDadosMercado;
	}

	public void setDiretorioDadosMercado(String diretorioDadosMercado) {
		this.diretorioDadosMercado = diretorioDadosMercado;
	}

	public String getDiretorioDadosOperacao() {
		return diretorioDadosOperacao;
	}

	public void setDiretorioDadosOperacao(String diretorioDadosOperacao) {
		this.diretorioDadosOperacao = diretorioDadosOperacao;
	}

	public List<Mercado> getMercados() {
		return mercados;
	}

	public void setMercados(List<Mercado> mercados) {
		this.mercados = mercados;
	}

	public List<Operacao> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(List<Operacao> operacoes) {
		this.operacoes = operacoes;
	}

	public FileWriter getEscritor() {
		return escritor;
	}

	public void setEscritor(FileWriter escritor) {
		this.escritor = escritor;
	}

	public void lerMercado() throws IOException{
		BufferedReader leitor = null;
		String linha = null;
		String divisor = ";";
		boolean primeiraLinha = true;
		Mercado mercado;
		mercados = new LinkedList<>();
		String t;
		String texto[];
		try {
			leitor = new BufferedReader(new FileReader(diretorioDadosMercado));
			int i=0;
			
			while( (linha = leitor.readLine()) !=null) {
				if(primeiraLinha) {
					primeiraLinha = false;
					continue;
				}
				t 		= linha.replace(",",".");
				texto = t.split(divisor);
				
				mercado = new Mercado();
				mercado.setId(i);
				mercado.setID_PRECO(texto[0]);
				mercado.setNU_PRAZO_DIAS_CORRIDOS(Long.parseLong(texto[1]) );
				mercado.setVL_PRECO(Double.parseDouble(texto[2]));
				i++;
				mercados.add(mercado);
			}
		}catch(FileNotFoundException exFile) {
			exFile.printStackTrace();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(leitor != null) {
				try {
					leitor.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
				
		}
		
	}
	
	public void lerOperacao()throws IOException {
		BufferedReader leitor = null;
		String linha = null;
		boolean primeiraLinha = true;
		Operacao operacao;
		operacoes = new LinkedList<>();
		
		String t;
		String texto[];
		DateFormat formatador = new SimpleDateFormat("dd/mm/yyyy");
		
		try {
			leitor = new BufferedReader(new FileReader(diretorioDadosOperacao));
			while( (linha = leitor.readLine()) !=null) {
				if(primeiraLinha) {
					primeiraLinha = false;
					continue;
				}
				t = linha.replace(",",".");
				texto = t.split(";");
				
				operacao = new Operacao();
				
				operacao.setDT_INICIO(
						new DateTime(formatador.parse(texto[1]))
				);
				
				operacao.setDT_FIM(
						new DateTime(formatador.parse(texto[2]))
				);
				operacao.setNM_SUBPRODUTO(texto[9]);
				operacao.setQUANTIDADE(Double.parseDouble(texto[12]));
				operacao.setID_PRECO(texto[13]);
			
				operacoes.add(operacao);
			}
		}catch(FileNotFoundException exFile) {
			exFile.printStackTrace();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(leitor != null) {
				try {
					leitor.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
				
		}
		
	}
	
	public double gerarResultado(Mercado mercado, Operacao operacao)throws IOException {
		return mercado.getVL_PRECO() * operacao.getQUANTIDADE();
	}
	
	public double gerarResultado(int id_preco, Operacao operacao)throws IOException {
		double resultado =  id_preco * operacao.getQUANTIDADE();
		if(resultado <=0)
			return 0;
		return resultado;
	}
	
	public List<Resultado> calcularResultados() throws IOException {
		
		List<Resultado> resultados = new ArrayList<>();
		for(Mercado mercado: mercados) {
			
			for(Operacao operacao: operacoes){
				
				if(operacao.getID_PRECO().equals(mercado.getID_PRECO()) &&
						operacao.calcularDataEmDias() == mercado.getNU_PRAZO_DIAS_CORRIDOS()) {
					resultado = new Resultado();
					resultado.setNM_SUBPRODUTO(operacao.getNM_SUBPRODUTO());
					resultado.setRESULTADO(gerarResultado(mercado, operacao));
					resultados.add(resultado);
				}
			}
			break;
		}
		return resultados;
	}
	
	public void gerarArquivoResultado() throws IOException{
		List<Resultado> resultados = calcularResultados();
		try {
			escritor = new FileWriter("result.csv");
			escritor.write("NM_SUBPRODUTO;RESULTADO;\n");
			for(Resultado resultado: resultados) {
				escritor.write(resultado.getNM_SUBPRODUTO()+";"+resultado.getRESULTADO()+"\n");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			escritor.flush();
			escritor.close();
		}
	}

}

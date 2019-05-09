package modelo;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class Operacao {
	private DateTime 	DT_INICIO;
	private DateTime 	DT_FIM;
	private String 		NM_SUBPRODUTO;
	private double 		QUANTIDADE;
	private String 		ID_PRECO;
	
	public Operacao() {}
	
	public int calcularDataEmDias() {
		return Days.daysBetween(DT_INICIO, DT_FIM).getDays();
	}
	
	public DateTime getDT_INICIO() {
		return DT_INICIO;
	}

	public void setDT_INICIO(DateTime dT_INICIO) {
		DT_INICIO = dT_INICIO;
	}

	public DateTime getDT_FIM() {
		return DT_FIM;
	}

	public void setDT_FIM(DateTime dT_FIM) {
		DT_FIM = dT_FIM;
	}

	public String getNM_SUBPRODUTO() {
		return NM_SUBPRODUTO;
	}

	public void setNM_SUBPRODUTO(String nM_SUBPRODUTO) {
		NM_SUBPRODUTO = nM_SUBPRODUTO;
	}

	public double getQUANTIDADE() {
		return QUANTIDADE;
	}

	public void setQUANTIDADE(double qUANTIDADE) {
		QUANTIDADE = qUANTIDADE;
	}

	public String getID_PRECO() {
		return ID_PRECO;
	}

	public void setID_PRECO(String iD_PRECO) {
		ID_PRECO = iD_PRECO;
	}

	
	
}

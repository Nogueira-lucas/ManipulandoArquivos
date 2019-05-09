package modelo;


public class Mercado {
	private long			id;
	private String 	ID_PRECO;
	private long 	NU_PRAZO_DIAS_CORRIDOS;
	private double 	VL_PRECO;
	
	public Mercado() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getID_PRECO() {
		return ID_PRECO;
	}

	public void setID_PRECO(String iD_PRECO) {
		ID_PRECO = iD_PRECO;
	}

	public long getNU_PRAZO_DIAS_CORRIDOS() {
		return NU_PRAZO_DIAS_CORRIDOS;
	}

	public void setNU_PRAZO_DIAS_CORRIDOS(long nU_PRAZO_DIAS_CORRIDOS) {
		NU_PRAZO_DIAS_CORRIDOS = nU_PRAZO_DIAS_CORRIDOS;
	}

	public double getVL_PRECO() {
		return VL_PRECO;
	}

	public void setVL_PRECO(double vL_PRECO) {
		VL_PRECO = vL_PRECO;
	}

	
	
	
	
	
	
}

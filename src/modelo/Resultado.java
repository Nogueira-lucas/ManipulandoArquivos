package modelo;

public class Resultado {
	private String NM_SUBPRODUTO;
	private double RESULTADO;
	
	public Resultado() {}
	
	public Resultado(String nM_SUBPRODUTO, double rESULTADO) {
		super();
		NM_SUBPRODUTO = nM_SUBPRODUTO;
		RESULTADO = rESULTADO;
	}

	public String getNM_SUBPRODUTO() {
		return NM_SUBPRODUTO;
	}

	public void setNM_SUBPRODUTO(String nM_SUBPRODUTO) {
		NM_SUBPRODUTO = nM_SUBPRODUTO;
	}

	public double getRESULTADO() {
		return RESULTADO;
	}

	public void setRESULTADO(double rESULTADO) {
		if(rESULTADO <= 0)
			RESULTADO = 0;
		RESULTADO = rESULTADO;
	}

	
}

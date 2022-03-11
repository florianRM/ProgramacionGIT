package vehiculos;

public enum CombustibleEnum {
	GASOLINA(3.5), DIESEL(2);
	
	private double precioCombustible;

	private CombustibleEnum(double precioCombustible) {
		this.precioCombustible = precioCombustible;
	}

	public double getPrecioCombustible() {
		return precioCombustible;
	}
	
}

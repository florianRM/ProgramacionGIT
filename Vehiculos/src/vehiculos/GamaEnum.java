package vehiculos;

public enum GamaEnum {
	BAJA(30), MEDIA(40), ALTA(50);
	
	private double precio;

	private GamaEnum(double precio) {
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}
	
}

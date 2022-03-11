package vehiculos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Coche extends Vehiculo {
	private CombustibleEnum combustible;

	public Coche(String matricula, String gama, LocalDate fechaSalida, String combustible) throws VehiculoException {
		super(matricula, gama, fechaSalida);
		this.combustible = CombustibleEnum.valueOf(combustible);
	}

	public String getCombustible() {
		return combustible.toString();
	}

	public void setCombustible(String combustible) {
		this.combustible = CombustibleEnum.valueOf(combustible);
	}

	@Override
	public String toString() {
		return "Coche [combustible=" + combustible + ", toString()=" + super.toString() + "]";
	}
	
	@Override
	public double getPrecio() {
		return (super.getPrecio() + this.combustible.getPrecioCombustible()) * this.getFechaEntrada().until(this.getFechaSalida(), ChronoUnit.DAYS);
	}
}
